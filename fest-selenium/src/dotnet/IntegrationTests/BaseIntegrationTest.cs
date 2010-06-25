// Date: Sept 4 2009
// Mel Llaguno
// http://www.aclaro.com
// -----------------------------------------
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// Note: This Test suite requires additional configuration:
//       - a webserver with the contents of the resource directory available in the web root directory.
//       - the latest versions of Internet Explorer, FireFox, Safari, and Opera
//       - the NUnit test runner.

using JavaSelenium;
using NUnit.Framework;

namespace IntegrationTests
{
    [TestFixture]
    public class BaseIntegrationTest
    {
        private const string URL = "http://localhost/liveconnect.html";
        private const string APPLET_URL = "http://localhost/applet.html";
        
        private const string HOST = "localhost";
        private const string METHOD_INVOCATION_OBJECT_ID = "method";
        private const string FIELD_ACCESS_OBJECT_ID = "field";
        private const string ARRAY_ACCESS_OBJECT_ID = "array";
        private const string PACKAGE_ACCESS_OBJECT_ID = "package";
        private const string APPLET_OBJECT_ID = "test_applet";

        private const int _PORT = 4444;
        
        [SetUp]
        public void SetUp() { }

        [TearDown]
        public void TearDown() { }

        private static IJavaSelenium _GetJavaSelenium(string pBrowserString, string pURL, string pObjectID)
        {
            return new JavaSelenium.JavaSelenium(HOST, _PORT, pBrowserString, pURL, pObjectID);
        }

        private static IJavaSelenium _GetJavaSelenium(string pBrowserString, string pObjectID)
        {
            return new JavaSelenium.JavaSelenium(HOST, _PORT, pBrowserString, URL, pObjectID);
        }

        /* Requires tests to ensure that the LiveConnect specification works against <object> 
         * tag. The original LiveConnect tests use the deprecated <applet> tag.
         */

        #region FireFox

        // Passes in 3.0.0.6 
        // Tested against JRE 1.6.0_11 / 1.6.0_16
        /* The following stack trace is thrown when the error is encountered 
         
Java Plug-in 1.6.0_11
Using JRE version 1.6.0_11 Java HotSpot(TM) Client VM
User home directory = C:\Documents and Settings\mel

----------------------------------------------------
c:   clear console window
f:   finalize objects on finalization queue
g:   garbage collect
h:   display this help message
l:   dump classloader list
m:   print memory usage
o:   trigger logging
q:   hide console
r:   reload policy configuration
s:   dump system and deployment properties
t:   dump thread list
v:   dump thread stack
x:   clear classloader cache
0-5: set trace level to <n>
----------------------------------------------------

testFunctionCall: test started
Calling JavaScript getString();
Got string from JavaScript: "Hello, world!"
Got number from JavaScript: 5
testFunctionCall: test passed.
testFieldAccess: test started
Calling JavaScript new cities();
Finished calling JavaScript
cities.a = Athens
cities.b = Belgrade
cities.c = Cairo
Set cities.b to Belfast
cities.a = Athens
cities.b = Belfast
cities.c = Cairo
Deleting cities.b
cities.a = Athens
cities.b = [undefined]
cities.c = Cairo
testFieldAccess: test passed.
testArrayAccess: test started
Calling JavaScript getTestArray();
Finished calling JavaScript getTestArray();
testArray[0] = foo
testArray[1] = bar
Trying to redefine testArray[1]
netscape.javascript.JSException
	at sun.plugin2.main.client.MessagePassingJSObject.newJSException(Unknown Source)
	at sun.plugin2.main.client.MessagePassingJSObject.waitForReply(Unknown Source)
	at sun.plugin2.main.client.MessagePassingJSObject.doSlotOp(Unknown Source)
	at sun.plugin2.main.client.MessagePassingJSObject.getSlot(Unknown Source)
	at JavaJSTest.testArrayAccess(JavaJSTest.java:111)
	at JavaJSTest.start(JavaJSTest.java:12)
	at sun.plugin2.applet.Plugin2Manager$AppletExecutionRunnable.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
Caused by: java.lang.InterruptedException
	at java.lang.Object.wait(Native Method)
	at sun.plugin2.message.Queue.waitForMessage(Unknown Source)
	at sun.plugin2.message.Pipe.receive(Unknown Source)
	... 7 more
testArrayAccess: TEST FAILED
2 tests passed, 1 tests failed
 
         */
        
        /* Note: FireFox 3.6 with Selenium-RC 1.0.1 fails to open the browser cleanly 
         * resulting in a locked file exception when generating the profile*/
        
        [Test]
        [Category("FireFox")]
        [Ignore("FireFox 3.0.0.15/3.5.3-3.5.7 is currently broken.")]
        public void TestMethodsInFireFox()
        {
            _GenericMethodAssertions(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        [ExpectedException(typeof(Selenium.SeleniumException))]
        public void TestNonExistentMethodInFireFox()
        {
            _GenericNonExistentMethodAssertions(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        public void TestFieldAccessInFireFox()
        {
            _GenericFieldAssertions(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        public void TestNonExistentFieldInFireFox()
        {
            _GenericNonExistentFieldAssertions(Browsers.FireFox.String);
        }

        // Passes in 3.0.0.6
        [Test]
        [Category("FireFox")]
        [Ignore("FireFox 3.0.0.15/3.5.3-3.5.7 is currently broken.")]
        public void TestArraysInFireFox()
        {
            _GenericArrayAssertions(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        public void TestPackagesInFireFox()
        {
            _GenericPackageAssertions(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("FireFox 3.0.0.15/3.5.3-3.5.7 is currently broken.")]
        public void TestObjectElementFireFox()
        {
            _GenericAppletAssertions(Browsers.FireFox.String);
        }

        #endregion

        #region Internet Explorer

        [Test]
        [Category("IE")]
        public void TestMethodsInIE()
        {
            _GenericMethodAssertions(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        [ExpectedException(typeof(Selenium.SeleniumException))]
        public void TestNonExistentMethodInIE()
        {
            _GenericNonExistentMethodAssertions(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestFieldAccessInIE()
        {
            _GenericFieldAssertions(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestNonExistentFieldInIE()
        {
            _GenericNonExistentFieldAssertions(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestArraysInIE()
        {
            _GenericArrayAssertions(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestPackagesInIE()
        {
            _GenericPackageAssertions(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestObjectElementIE()
        {
            _GenericAppletAssertions(Browsers.IE.String);
        }

        #endregion

        #region Safari

        [Test]
        [Category("Safari")]
        [Ignore("Safari 3.2.3/4 crashes the test runner.")]
        public void TestMethodsInSafari()
        {
            _GenericMethodAssertions(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [ExpectedException(typeof(Selenium.SeleniumException))]
        [Ignore("Safari 3.2.3/4 crashes the test runner.")]
        public void TestNonExistentMethodInSafari()
        {
            _GenericNonExistentMethodAssertions(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Safari 3.2.3/4 crashes the test runner.")]
        public void TestFieldAccessInSafari()
        {
            _GenericFieldAssertions(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Safari 3.2.3/4 crashes the test runner.")]
        public void TestNonExistentFieldInSafari()
        {
            _GenericNonExistentFieldAssertions(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Safari 3.2.3/4 crashes the test runner.")]
        public void TestArraysInSafari()
        {
            _GenericArrayAssertions(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Safari 3.2.3/4 crashes the test runner.")]
        public void TestPackagesInSafari()
        {
            _GenericPackageAssertions(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Safari 3.2.3/4 crashes the test runner.")]
        public void TestObjectElementSafari()
        {
            _GenericAppletAssertions(Browsers.Safari.String);
        }

        #endregion

        #region Opera

        [Test]
        [Category("Opera")]
        public void TestMethodsInOpera()
        {
            //_GenericMethodAssertions(Browsers.Opera.String);
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(Browsers.Opera.String, METHOD_INVOCATION_OBJECT_ID))
            {
                javaSelenium.Selenium.Start();
                javaSelenium.Selenium.Open(URL);

                javaSelenium.InvokeMethod("noArgMethod");

                javaSelenium.InvokeMethod("someMethod", 5);
                javaSelenium.InvokeMethod("someMethod", "Hello");

                Assert.AreEqual("5", javaSelenium.InvokeMethod("methodReturningInt"));
                
                // BUG: For some strange reason, this method does not work
                //Assert.AreEqual("Hello", javaSelenium.InvokeMethod("methodReturningString"));

                javaSelenium.InvokeMethod("methodReturningObject().anotherMethod");
            }
        }

        [Test]
        [Category("Opera")]
        [ExpectedException(typeof(Selenium.SeleniumException))]
        public void TestNonExistentMethodInOpera()
        {
            _GenericNonExistentMethodAssertions(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        public void TestFieldAccessInOpera()
        {
            //_GenericFieldAssertions(Browsers.Opera.String);

            using (IJavaSelenium javaSelenium = _GetJavaSelenium(Browsers.Opera.String, FIELD_ACCESS_OBJECT_ID))
            {
                javaSelenium.Selenium.Start();
                javaSelenium.Selenium.Open(URL);

                Assert.AreEqual("5", javaSelenium.GetField("intField"));
                javaSelenium.SetField("intField", 6);
                Assert.AreEqual("6", javaSelenium.GetField("intField"));

                // BUG: It seems that return values that are strings are affected
                //Assert.AreEqual("Hello", javaSelenium.GetField("stringField"));
                //javaSelenium.SetField("stringField", "Goodbye");
                //Assert.AreEqual("Goodbye", javaSelenium.GetField("stringField"));

                Assert.AreEqual("6", javaSelenium.GetField("otherField.intField"));
                javaSelenium.SetField("otherField.intField", 7);
                Assert.AreEqual("7", javaSelenium.GetField("otherField.intField"));

                // BUG: It seems that return values that are strings are affected
                //Assert.AreEqual("Testing", javaSelenium.GetField("otherField.stringField"));
                //javaSelenium.SetField("otherField.stringField", "1, 2, 3");
                //Assert.AreEqual("1, 2, 3", javaSelenium.GetField("otherField.stringField"));
            }
        }

        [Test]
        [Category("Opera")]
        public void TestNonExistentFieldInOpera()
        {
            _GenericNonExistentFieldAssertions(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        public void TestArraysInOpera()
        {
            _GenericArrayAssertions(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Liveconnect package implementation broken in Opera.")]
        public void TestPackagesInOpera()
        {
            _GenericPackageAssertions(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        public void TestObjectElementOpera()
        {
            _GenericAppletAssertions(Browsers.Opera.String);
        }

        #endregion

        #region Private Methods

        private static void _GenericAppletAssertions(string pBrowserString)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, APPLET_URL, APPLET_OBJECT_ID))
            {
                javaSelenium.Selenium.Open(APPLET_URL);

                javaSelenium.InvokeMethod("init");
            }
        }

        private static void _GenericPackageAssertions(string pBrowser)
        {
            using(IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowser, PACKAGE_ACCESS_OBJECT_ID))
            {
                javaSelenium.Selenium.Open(URL);

                Assert.AreEqual("5", javaSelenium.GetField("Packages.com.mycompany.MyClass.staticField"));
                javaSelenium.SetField("Packages.com.mycompany.MyClass.staticField", 6);
                Assert.AreEqual("6", javaSelenium.GetField("Packages.com.mycompany.MyClass.staticField"));

                javaSelenium.InvokeMethod("Packages.com.mycompany.MyClass.staticMethod");
            }
        }

        private static void _GenericArrayAssertions(string pBrowser)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowser, ARRAY_ACCESS_OBJECT_ID))
            { 
                javaSelenium.Selenium.Open(URL);

                Assert.AreEqual("1,2,3", javaSelenium.InvokeMethod("returns123"));

                // Note: This will work in Opera; it seems if the return value is a String [] then all is good
                Assert.AreEqual("Hello,world", javaSelenium.InvokeMethod("returnsHelloWorld"));

                // Bug: Multi-dimensonal arrays are broken - looks like they return an array of pointers
                //Assert.AreEqual("[[1, 2, 3][4, 5, 6][7, 8, 9]]", javaSelenium.InvokeMethod("returns1Through9"));
            }
        }

        private static void _GenericFieldAssertions(string pBrowser)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowser, FIELD_ACCESS_OBJECT_ID))
            {
                javaSelenium.Selenium.Open(URL);

                Assert.AreEqual("5", javaSelenium.GetField("intField"));
                javaSelenium.SetField("intField", 6);
                Assert.AreEqual("6", javaSelenium.GetField("intField"));

                Assert.AreEqual("Hello", javaSelenium.GetField("stringField"));
                javaSelenium.SetField("stringField", "Goodbye");
                Assert.AreEqual("Goodbye", javaSelenium.GetField("stringField"));

                Assert.AreEqual("6", javaSelenium.GetField("otherField.intField"));
                javaSelenium.SetField("otherField.intField", 7);
                Assert.AreEqual("7", javaSelenium.GetField("otherField.intField"));

                Assert.AreEqual("Testing", javaSelenium.GetField("otherField.stringField"));
                javaSelenium.SetField("otherField.stringField", "1, 2, 3");
                Assert.AreEqual("1, 2, 3", javaSelenium.GetField("otherField.stringField"));

            }
        }

        private static void _GenericNonExistentFieldAssertions(string pBrowser)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowser, FIELD_ACCESS_OBJECT_ID))
            {
                javaSelenium.Selenium.Open(URL);

                //////////////////////////////////////////////////////////////////////////
                // WARNING : Unlike calling an non-existent method, calling a non-existent
                //           field returns "null"
                //////////////////////////////////////////////////////////////////////////
                
                Assert.AreEqual("null", javaSelenium.GetField("nonExistentField"));
            }
        }

        private static void _GenericMethodAssertions(string pBrowser)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowser, METHOD_INVOCATION_OBJECT_ID))
            {
                javaSelenium.Selenium.Open(URL);

                javaSelenium.InvokeMethod("noArgMethod");

                javaSelenium.InvokeMethod("someMethod", 5);
                javaSelenium.InvokeMethod("someMethod", "Hello");

                Assert.AreEqual("5", javaSelenium.InvokeMethod("methodReturningInt"));
                Assert.AreEqual("Hello", javaSelenium.InvokeMethod("methodReturningString"));

                javaSelenium.InvokeMethod("methodReturningObject().anotherMethod");
            }
        }

        private static void _GenericNonExistentMethodAssertions(string pBrowser)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowser, METHOD_INVOCATION_OBJECT_ID))
            {
                javaSelenium.Selenium.Open(URL);

                Assert.Fail(javaSelenium.InvokeMethod("methodDoesNotExist"));
            }
        }

        #endregion
    }
}
