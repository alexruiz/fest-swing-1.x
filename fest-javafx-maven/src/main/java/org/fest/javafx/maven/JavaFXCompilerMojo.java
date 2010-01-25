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

import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.*;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.taskdefs.Javac;

/**
 * Compiles JavaFX source code by delegating to JavaFX's own compiler Ant task.
 * @goal compile
 * @phase compile
 * @requiresDependencyResolution compile
 *
 * @author Alex Ruiz
 */
public class JavaFXCompilerMojo extends AbstractMojo {

  /**
   * The current Maven project
   * @parameter expression="${project}"
   * @readonly
   */
  MavenProject project;

  /**
   * The list of compile classpath elements.
   * @parameter default-value="${project.compileClasspathElements}"
   * @requiresDependencyResolution compile
   * @required
   * @readonly
   */
  List<String> compileClasspathElements;

  /**
   * The directory for compiled classes.
   * @parameter default-value="${project.build.outputDirectory}"
   * @required
   * @readonly
   */
  File outputDirectory;

  /**
   * The location of the JavaFX home directory. If a value is not set, this goal will try to obtained from the
   * environment variable "JAVAFX_HOME".
   * @parameter expression="${javafx.home}"
   */
  String JavaFXHome;

  /**
   * The source directory.
   * @parameter expression="${javafx.compiler.sourceDirectory}" default-value="${basedir}/src/main/javafx"
   * @required
   */
  File sourceDirectory;

  /**
   * Set to <code>true</code> to include debugging information in the compiled class files.
   * @parameter expression="${javafx.compiler.debug}" default-value="true"
   */
  boolean debug = true;

  /**
   * Indicates whether the build will continue even if there are compilation errors; defaults to <code>true</code>.
   * @parameter expression="${javafx.compiler.failOnError}" default-value="true"
   */
  boolean failOnError = true;

  /**
   * Allows running the compiler in a separate process. If <code>false</code> it uses the built in compiler, while if
   * <code>true</code> it will use an executable.
   * @parameter expression="${javafx.compiler.fork}" default-value="false"
   */
  boolean fork;

  /**
   * Sets the executable of the compiler to use when fork is true.
   * @parameter expression="${javafx.compiler.executable}"
   */
  String forkExecutable;

  /**
   * Set to <code>true</code> to show messages about what the compiler is doing.
   * @parameter expression="${javafx.compiler.verbose}" default-value="false"
   */
  boolean verbose;

  /**
   * Sets whether to show source locations where deprecated APIs are used.
   * @parameter expression="${javafx.compiler.showDeprecation}" default-value="false"
   */
  boolean deprecation;

  /**
   * The source files character encoding.
   * @parameter expression="${javafx.compiler.encoding}" default-value="UTF-8"
   */
  String encoding;

  /**
   * Set to <code>true</code> to optimize the compiled code using the compiler's optimization methods.
   * @parameter expression="${javafx.compiler.optimize}" default-value="false"
   */
  boolean optimize;

  /**
   * The -source argument for the JavaFX compiler.
   * @parameter expression="${javafx.compiler.source}" default-value="1.6"
   */
  String source;

  /**
   * The -target argument for the JavaFX compiler.
   * @parameter expression="${javafx.compiler.target}" default-value="1.6"
   */
  String target;

  private JavaFXHome javaFXHome;
  private JavaFXCompilerFactory compilerFactory;
  private JavaFXCompilerSetup compilerSetup;
  private AntTaskExecutor compilerExecutor;

  /**
   * Creates a new </code>{@link JavaFXCompilerMojo}</code>.
   */
  public JavaFXCompilerMojo() {
    javaFXHome(new JavaFXHome());
    compilerFactory(new JavaFXCompilerFactory());
    compilerSetup(new JavaFXCompilerSetup());
    compilerExecutor(new AntTaskExecutor());
  }

  /**
   * Calls the JavaFX compiler Ant task to compile JavaFX sources.
   * @throws MojoExecutionException if the specified source directory does not exist or it is not a directory.
   * @throws MojoExecutionException if the output directory does not exist and cannot be created.
   * @throws MojoExecutionException if the JavaFX compiler Ant task cannot be instantiated.
   * @throws MojoExecutionException if the JavaFX home directory has not being set.
   * @throws MojoExecutionException if the location specified by as the JavaFX home directory does not exist or it is
   * not a directory.
   */
  public void execute() throws MojoExecutionException {
    validateSourceDirectory();
    validateOutputDirectory();
    String verifiedJavaFXHome = javaFXHome.verify(JavaFXHome);
    getLog().info(concat("JavaFX home is ", quote(verifiedJavaFXHome)));
    File javaFXHomeDir = javaFXHome.reference(verifiedJavaFXHome);
    Javac javafxc = compilerFactory.createCompiler(javaFXHomeDir);
    compilerSetup.configure(javafxc, this, javaFXHomeDir);
    compilerExecutor.execute(javafxc);
  }

  // package-protected for testing only
  void validateSourceDirectory() throws MojoExecutionException {
    if (sourceDirectory.isDirectory()) return;
    throw new MojoExecutionException("Source directory is not an existing directory.");
  }

  // package-protected for testing only
  void validateOutputDirectory() throws MojoExecutionException {
    if (outputDirectory.isDirectory()) return;
    boolean success = outputDirectory.mkdirs();
    if (!success) throw new MojoExecutionException("Unable to create output directory");
  }

  void javaFXHome(JavaFXHome newJavaFXHome) {
    javaFXHome = newJavaFXHome;
  }

  void compilerFactory(JavaFXCompilerFactory newCompilerFactory) {
    compilerFactory = newCompilerFactory;
  }

  void compilerSetup(JavaFXCompilerSetup newCompilerSetup) {
    compilerSetup = newCompilerSetup;
  }

  void compilerExecutor(AntTaskExecutor newCompilerExecutor) {
    compilerExecutor = newCompilerExecutor;
  }
}
