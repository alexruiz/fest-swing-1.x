/*
 * Created on Mar 30, 2010
 * Modified on July 05, 2010
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
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.JApplet;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;

/**
 * Understands functional testing of <code>{@link JApplet}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the
 * package <code>{@link org.fest.swing.fixture}</code> in your tests.
 * 
 * @author Mel Llaguno
 * @author Alex Ruiz
 * 
 * @since 1.2
 */
public class JAppletDriver extends ComponentDriver implements AppletStub {

	private JApplet _applet;
	
	/**
	 * Creates a new </code>{@link JAppletDriver}</code>.
	 * 
	 * @param robot the robot used to simulate user input.
	 */
	public JAppletDriver(Robot robot) {
		super(robot);
	}

	/**
	 * Creates a new <code>{@link JAppletDriver}</code>.
	 * 
	 * @param robot the robot used to simulate user input.
	 * @param newApplet the applet to simulate user input against.
	 */
	public JAppletDriver(Robot robot, JApplet newApplet){
		this(robot);
		_applet = newApplet;
	}
	
	@RunsInEDT
	private static AppletContext appletContext(final JApplet applet) {
		return execute(new GuiQuery<AppletContext>() {
			protected AppletContext executeInEDT() {
				return applet.getAppletContext();
			}
		});
	}
	
	@RunsInEDT
	private static void doResize(final JApplet applet, final int width,
			final int height) {
		execute(new GuiTask() {
			protected void executeInEDT() {
				applet.resize(width, height);
			}
		});
	}
	
	@RunsInEDT
	private static URL codeBase(final JApplet applet) {
		return execute(new GuiQuery<URL>() {
			protected URL executeInEDT() {
				return applet.getCodeBase();
			}
		});
	}
	
	@RunsInEDT
	private static URL documentBase(final JApplet applet) {
		return execute(new GuiQuery<URL>() {
			protected URL executeInEDT() {
				return applet.getDocumentBase();
			}
		});
	}

	@RunsInEDT
	private static String parameter(final JApplet applet,
			final String parameterName) {
		return execute(new GuiQuery<String>() {
			protected String executeInEDT() {
				return applet.getParameter(parameterName);
			}
		});
	}

	@RunsInEDT
	private static boolean active(final JApplet applet) {
		return execute(new GuiQuery<Boolean>() {
			protected Boolean executeInEDT() {
				return applet.isActive();
			}
		});
	}

	/**
	 * Requests the default <code>{@link JApplet}</code> to be resized.
	 * 
	 * @param width
	 *            the new width.
	 * @param height
	 *            the new height.
	 */
	@RunsInEDT
	public void appletResize(int width, int height) {
		appletResize(_applet, width, height);
	}
	
	/**
	 * Requests the given <code>{@link JApplet}</code> to be resized.
	 * 
	 * @param applet
	 *            the given {@code JApplet}.
	 * @param width
	 *            the new width.
	 * @param height
	 *            the new height.
	 */
	@RunsInEDT
	public void appletResize(JApplet applet, int width, int height){
		doResize(applet, width, height);
	}

	/**
	 * Returns the <code>{@link AppletContext}</code> of the default
	 * <code>{@link JApplet}</code>.
	 * 
	 * @return the {@code AppletContext} of the given {@code JApplet}.
	 */
	@RunsInEDT
	public AppletContext getAppletContext() {
		return getAppletContext(_applet);
	}
	
	/**
	 * Returns the <code>{@link AppletContext}</code> of the given
	 * <code>{@link JApplet}</code>.
	 * 
	 * @param applet
	 *            the given {@code JApplet}.
	 * @return the {@code AppletContext} of the given {@code JApplet}.
	 */
	@RunsInEDT
	public AppletContext getAppletContext(JApplet applet){
		return appletContext(applet);
	}

	/**
	 * Returns the URL of the directory that contains the default
	 * <code>{@link JApplet}</code>.
	 * 
	 * @return the URL of the directory that contains the given {@code JApplet}.
	 */
	@RunsInEDT
	public URL getCodeBase() {
		return getCodeBase(_applet);
	}

	/**
	 * Returns the URL of the directory that contains the given
	 * <code>{@link JApplet}</code>.
	 * 
	 * @param applet
	 *            the given {@code JApplet}.
	 * @return the URL of the directory that contains the given {@code JApplet}.
	 */
	@RunsInEDT
	public URL getCodeBase(JApplet applet){
		return codeBase(_applet);
	}
	
	/**
	 * Returns the URL of the document the default <code>{@link JApplet}</code>
	 * is embedded.
	 * 
	 * @return the URL of the document the given {@code JApplet} is embedded.
	 */
	@RunsInEDT
	public URL getDocumentBase() {
		return getDocumentBase(_applet);
	}

	/**
	 * Returns the URL of the document the given <code>{@link JApplet}</code>
	 * is embedded.
	 * 
	 * @param applet
	 *            the given {@code JApplet}.
	 * @return the URL of the document the given {@code JApplet} is embedded.
	 */
	@RunsInEDT
	public URL getDocumentBase(JApplet applet){
		return documentBase(applet);
	}
	
	/**
	 * Returns the value of the named parameter in the default
	 * <code>{@link JApplet}</code> in the HTML tag, or <code>null</code> if
	 * not set.
	 * 
	 * @param name
	 *            a parameter name.
	 * @return the value of the named parameter in the given {code JApplet} in
	 *         the HTML tag, or <code>null</code> if not set.
	 */
	@RunsInEDT
	public String getParameter(String name) {
		return getParameter(_applet, name);
	}

	/**
	 * Returns the value of the named parameter in the given
	 * <code>{@link JApplet}</code> in the HTML tag, or <code>null</code> if
	 * not set.
	 * 
	 * @param applet
	 *            the given {@code JApplet}.
	 * @param name
	 *            a parameter name.
	 * @return the value of the named parameter in the given {code JApplet} in
	 *         the HTML tag, or <code>null</code> if not set.
	 */
	@RunsInEDT
	public String getParameter(JApplet applet, String name){
		return parameter(applet, name);
	}
	
	/**
	 * Indicates whether the default <code>{@link JApplet}</code> is active or
	 * not.
	 * 
	 * @return <code>true</code> if the default {@code JApplet} is active;
	 * 		   <code>false</code> otherwise.
	 */
	@RunsInEDT
	public boolean isActive() {
		return isActive(_applet);
	}
	
	/**
	 * Indicates whether the given <code>{@link JApplet}</code> is active or
	 * not.
	 * 
	 * @param applet
	 *            the given {@code JApplet}.
	 * @return <code>true</code> if the given {@code JApplet} is active;
	 *         <code>false</code> otherwise.
	 */
	@RunsInEDT
	public boolean isActive(JApplet applet){
		return active(applet);
	}
	
	/**
	 * Returns the Applet of the given its name in the AppletContext
	 * 
	 * @param name
	 *            the name of the {@code JApplet}.
	 * @return the {@code Applet} with the given name
	 */
	@RunsInEDT
	public Applet getApplet(String name) {
				return _applet.getAppletContext().getApplet(name);
	}
	
	/**
	 * Returns the collection Applets in the AppletContext
	 * 
	 * @return the collection of {@code Applet}s within the AppletContext
	 */
	@RunsInEDT
	public Enumeration<Applet> getApplets() {
				return _applet.getAppletContext().getApplets();
	}
}
