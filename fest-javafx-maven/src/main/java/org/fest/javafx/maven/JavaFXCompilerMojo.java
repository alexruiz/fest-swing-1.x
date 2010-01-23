/*
 * Created on Jan 20, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.maven;

import static org.fest.javafx.maven.AntAdapter.*;
import static org.fest.javafx.maven.JavaFXCompilerClasspath.JAVAFX_COMPILER_CLASSPATH_FILE_NAMES;
import static org.fest.javafx.maven.JavaFXCompilerClasspath.JAVAFX_DESKTOP_CLASSPATH_FILE_PATTERNS;
import static org.fest.javafx.maven.JavaFXHome.javaFXHomeDirectory;
import static org.fest.javafx.maven.JavaFXHome.verifiedJavaFXHome;
import static org.fest.reflect.core.Reflection.method;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.*;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.*;
import org.fest.reflect.exception.ReflectionError;

/**
 * Understands a Mojo that wraps JavaFX's Ant Task to be executed as a Maven plugin.
 * @goal compile
 * @phase compile
 * @requiresDependencyResolution compile
 *
 * @author Alex Ruiz
 */
public class JavaFXCompilerMojo extends AbstractMojo {

  private final JavaFXCompilerAntTaskFactory taskFactory;

  /**
   * The current Maven project
   * @parameter expression="${project}"
   * @readonly
   */
  private MavenProject project;

  /**
   * The list of compile classpath elements.
   * @parameter default-value="${project.compileClasspathElements}"
   * @requiresDependencyResolution compile
   * @required
   * @readonly
   */
  private List<String> compileClasspathElements;

  /**
   * The directory for compiled classes.
   * @parameter default-value="${project.build.outputDirectory}"
   * @required
   * @readonly
   */
  private File outputDirectory;

  /**
   * The location of the JavaFX home directory.
   * @parameter expression="${javafx.home}"
   */
  private String javaFXHome;

  /**
   * The source directory.
   * @parameter expression="${javafx.compiler.sourceDirectory}" default-value="${basedir}/src/main/javafx"
   * @required
   */
  private File sourceDirectory;

  /**
   * Sets to <code>true</code> to include debugging information in the compiled class files.
   * @parameter expression="${javafx.compiler.debug}" default-value="true"
   */
  private boolean debug = true;

  /**
   * Indicates whether the build will continue even if there are compilation errors; defaults to <code>true</code>.
   * @parameter expression="${javafx.compiler.failOnError}" default-value="true"
   */
  private boolean failOnError = true;

  /**
   * Allows running the compiler in a separate process. If <code>false</code> it uses the built in compiler, while if
   * <code>true</code> it will use an executable.
   * @parameter expression="${javafx.compiler.fork}" default-value="false"
   */
  private boolean fork;

  /**
   * Sets the executable of the compiler to use when fork is true.
   * @parameter expression="${javafx.compiler.executable}"
   */
  private String forkExecutable;

  /**
   * Sets to <code>true</code> to show messages about what the compiler is doing.
   * @parameter expression="${javafx.compiler.verbose}" default-value="false"
   */
  private boolean verbose;

  /**
   * Sets whether to show source locations where deprecated APIs are used.
   * @parameter expression="${javafx.compiler.showDeprecation}" default-value="false"
   */
  private boolean deprecation;

  /**
   * The source files character encoding.
   * @parameter expression="${javafx.compiler.encoding}" default-value="UTF-8"
   */
  private String encoding;

  /**
   * Sets to <code>true</code> to optimize the compiled code using the compiler's optimization methods.
   * @parameter expression="${javafx.compiler.optimize}" default-value="false"
   */
  private boolean optimize;

  /**
   * The -source argument for the JavaFX compiler.
   * @parameter expression="${javafx.compiler.source}" default-value="1.6"
   */
  private String source;

  /**
   * The -target argument for the JavaFX compiler.
   * @parameter expression="${javafx.compiler.target}" default-value="1.6"
   */
  private String target;

  /**
   * Creates a new </code>{@link JavaFXCompilerMojo}</code>.
   */
  public JavaFXCompilerMojo() {
    this(new JavaFXCompilerAntTaskFactory());
  }

  // package-protected for testing only
  JavaFXCompilerMojo(JavaFXCompilerAntTaskFactory taskFactory) {
    this.taskFactory = taskFactory;
  }

  /** {@inheritDoc} */
  public void execute() throws MojoExecutionException, MojoFailureException {
    validateSourceDirectory();
    String verifiedJavaFXHome = verifiedJavaFXHome(javaFXHome);
    getLog().info(concat("JavaFX home is ", quote(verifiedJavaFXHome)));
    File javaFXHomeDirectory = javaFXHomeDirectory(verifiedJavaFXHome);
    Javac javafxc = taskFactory.createJavaFXCompilerAntTask(javaFXHomeDirectory);
    configureCompiler(javafxc, javaFXHomeDirectory);
    try {
      javafxc.execute();
    } catch (BuildException e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

  private void validateSourceDirectory() throws MojoFailureException {
    if (sourceDirectory.isDirectory()) return;
    throw new MojoFailureException(concat("Source directory ", quote(sourceDirectory.getAbsolutePath()),
        " is not an existing directory."));
  }

  private void configureCompiler(Javac javafxc, File javaFXHomeDirectory) throws MojoExecutionException {
    setProject(javafxc);
    setCompilerClasspath(javafxc, javaFXHomeDirectory);
    setSource(javafxc);
    setClasspath(javafxc, javaFXHomeDirectory);
    setOutputDirectory(javafxc);
    javafxc.setDebug(debug);
    javafxc.setDeprecation(deprecation);
    javafxc.setEncoding(encoding);
    javafxc.setExecutable(forkExecutable);
    javafxc.setFailonerror(failOnError);
    javafxc.setFork(fork);
    javafxc.setOptimize(optimize);
    javafxc.setSource(source);
    javafxc.setTarget(target);
    javafxc.setVerbose(verbose);
  }

  private void setProject(Javac javafxc) {
    Project antProject = createAntProject(project, getLog());
    javafxc.setProject(antProject);
  }

  private void setCompilerClasspath(Javac javafxc, File javaFXHomeDirectory) throws MojoExecutionException {
    Path path = createCompilerClasspath(javafxc.getProject(), javaFXHomeDirectory);
    try {
      method("setCompilerClassPath").withParameterTypes(Path.class).in(javafxc).invoke(path);
    } catch (ReflectionError e) {
      throw new MojoExecutionException("Unable to set the compiler classpath", e);
    }
  }

  private static Path createCompilerClasspath(Project project, File javaFXHomeDirectory) {
    FileSet files = new FileSet();
    files.setDir(javaFXHomeDirectory);
    for (String include : JAVAFX_COMPILER_CLASSPATH_FILE_NAMES)
      files.createInclude().setName(concat("**/", include));
    Path path = new Path(project);
    path.addFileset(files);
    return path;
  }

  private void setSource(Javac javafxc) {
    updatePathWithFiles(javafxc.createSrc(), sourceDirectory);
    updatePathWithFiles(javafxc.createSourcepath(), sourceDirectory);
  }

  private void setClasspath(Javac javafxc, File javaFXHomeDirectory) {
    Path classpath = javafxc.createClasspath();
    FileSet javaFXFiles = new FileSet();
    javaFXFiles.setDir(javaFXHomeDirectory);
    for (String include : JAVAFX_DESKTOP_CLASSPATH_FILE_PATTERNS)
      javaFXFiles.createInclude().setName(include);
    classpath.addFileset(javaFXFiles);
    updatePathWithPaths(classpath, compileClasspathElements);
  }

  private void setOutputDirectory(Javac javafxc) {
    if (!outputDirectory.exists()) outputDirectory.mkdirs();
    javafxc.setDestdir(outputDirectory);
  }
}
