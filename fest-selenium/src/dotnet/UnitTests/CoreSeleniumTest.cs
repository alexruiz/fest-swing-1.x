//
// Java Selenium - .NET Client
// 
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

using JavaSelenium;
using NMock;
using NUnit.Framework;
using Selenium;

namespace UnitTests
{
    [TestFixture]
    public class CoreSeleniumTest
    {
        private const string OBJECT_ID = "test";

        private DynamicMock _seleniumMock;

        [SetUp]
        public void SetUp() 
        {
            _seleniumMock = new DynamicMock(typeof (ISelenium));
        }

        [TearDown]
        public void TearDown()
        { 
            _seleniumMock.Verify();
        }

        [Test]
        public void invokeSeleniumStart()
        {
            _seleniumMock.Expect("Start");        
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;
            using (new JavaSelenium.JavaSelenium(selenium, OBJECT_ID) )
            {
                try
                {
                    //Not necessary; invoked on JavaSelenium instantiation
                    //javaSelenium.Selenium.Start();
                }
                catch
                {
                    Assert.Fail("Selenium.Start should not fail.");
                }
            }
        }

        [Test]
        public void invokeSeleniumStop()
        {
            _seleniumMock.Expect("Stop");
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;
            IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID);
            
            // don't wrap in using as this will cause Stop to be called TWICE.
            try
            {
                    javaSelenium.Selenium.Stop();
            }
            catch
            {
                    Assert.Fail("Selenium.Stop should not fail.");
            }
        }

        [Test]
        public void invokeSeleniumOpenURL()
        {
            string url = "http://nowhere.com";

            _seleniumMock.Expect( "Open", new object[] {url} );
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;
            
            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID))
            {
                try
                {
                    javaSelenium.Selenium.Open(url);
                }
                catch
                {
                    Assert.Fail("Selenium.Open should not fail.");
                }
            }
        }

        [Test]
        public void invokeWaitForPageLoad()
        {
            string seconds = "5000";
            
            _seleniumMock.Expect( "WaitForPageToLoad", new object[] { seconds} );
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;

            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID))
            {
                try
                {
                    javaSelenium.Selenium.WaitForPageToLoad(seconds);
                }
                catch
                {
                    Assert.Fail("Selenium.WaitForPageToLoad should not fail.");
                }
            }
        }

        [Test]
        public void invokeCallMethod()
        {
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;

            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID))
            {
                try
                {
                    javaSelenium.InvokeMethod("Method");
                }
                catch
                {
                    Assert.Fail("The InvokeMethod method should not fall.");
                }
            }
        }

        [Test]
        public void invokeCallField()
        {
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;

            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID))
            {
                try
                {
                    javaSelenium.GetField("Field");
                }
                catch
                {
                    Assert.Fail("The GetField method should not fail.");
                }
            }
        }

        [Test]
        public void invokeObjectID()
        {
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;

            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID))
            {
                try
                {
                    Assert.AreEqual(OBJECT_ID, javaSelenium.ObjectID, "The default ObjectID property did not match the specified value in the constructor");

                    string newObjectID = "anotherObject";
                    javaSelenium.ObjectID = newObjectID;

                    Assert.AreEqual(newObjectID, javaSelenium.ObjectID, "The ObjectID property is not equal to the assigned value.");
                }
                catch
                {
                    Assert.Fail("The ObjectID property test should not fail.");
                } 
            }
        }

        [Test]
        public void invokeSelenium()
        {
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;

            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID))
            {
                try
                {
                    Assert.AreSame(selenium, javaSelenium.Selenium);
                }
                catch
                {
                    Assert.Fail("The Selenium property test should not fail.");
                }
            }
        }

        [Test]
        public void invokeDispose()
        {
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;

            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID))
            {
                try
                {
                    javaSelenium.Dispose();
                }
                catch
                {
                    Assert.Fail("The Dispose method should not fail.");
                }
            }
        }

        [Test]
        public void invokeGetJSPrefixGeneric()
        {
            _seleniumMock.ExpectAndReturn("GetEval", "RandomUserAgentInfo", new object[] {"navigator.userAgent"});
            ISelenium selenium = (ISelenium) _seleniumMock.MockInstance;
            
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(selenium, OBJECT_ID))
            {
                Assert.AreEqual("document.getElementById(\"" + OBJECT_ID + "\").", javaSelenium.JSPrefix);
            }
        }

        [Test]
        public void invokeGetJSPrefixForFireFox()
        {
            _GenericGetJSPrefix(Browsers.FireFox.AgentInfo);
        }

        [Test]
        public void invokeReturnJSPrefixForIE()
        {
            _GenericGetJSPrefix(Browsers.IE.AgentInfo);
        }

        [Test]
        public void invokeReturnJSPrefixForSafari()
        {
            _GenericGetJSPrefix(Browsers.Safari.AgentInfo);
        }

        [Test]
        public void invokeReturnJSPrefixForOpera()
        {
            _GenericGetJSPrefix(Browsers.Opera.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction1Firefox()
        {
            _GenericFunction1(Browsers.FireFox.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction2Firefox()
        {
            _GenericFunction2(Browsers.FireFox.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction3Firefox()
        {
            _GenericFunction3(Browsers.FireFox.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction1IE()
        {
            _GenericFunction1(Browsers.IE.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction2IE()
        {
            _GenericFunction2(Browsers.IE.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction3IE()
        {
            _GenericFunction3(Browsers.IE.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction1Safari()
        {
            _GenericFunction1(Browsers.Safari.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction2Safari()
        {
            _GenericFunction2(Browsers.Safari.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction3Safari()
        {
            _GenericFunction3(Browsers.Safari.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction1Opera()
        {
            _GenericFunction1(Browsers.Opera.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction2Opera()
        {
            _GenericFunction2(Browsers.Opera.AgentInfo);
        }

        [Test]
        public void CreateJSForFunction3Opera()
        {
            _GenericFunction3(Browsers.Opera.AgentInfo);
        }

        [Test]
        public void CreateJSForGetField1Firefox()
        {
            _GenericGetField1(Browsers.FireFox.AgentInfo);
        }

        [Test]
        public void CreateJSForGetField1IE()
        {
            _GenericGetField1(Browsers.IE.AgentInfo);
        }

        [Test]
        public void CreateJSForGetField1Safari()
        {
            _GenericGetField1(Browsers.Safari.AgentInfo);
        }

        [Test]
        public void CreateJSForGetField1Opera()
        {
            _GenericGetField1(Browsers.Opera.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField1Firefox()
        {
            _GenericSetField1(Browsers.FireFox.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField1IE()
        {
            _GenericSetField1(Browsers.IE.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField1Safari()
        {
            _GenericSetField1(Browsers.Safari.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField1Opera()
        {
            _GenericSetField1(Browsers.Opera.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField2Firefox()
        {
            _GenericSetField2(Browsers.FireFox.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField2IE()
        {
            _GenericSetField2(Browsers.IE.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField2Safari()
        {
            _GenericSetField2(Browsers.Safari.AgentInfo);
        }

        [Test]
        public void CreateJSForSetField2Opera()
        {
            _GenericSetField2(Browsers.Opera.AgentInfo);
        }

        #region Private Methods
        private void _GenericGetJSPrefix(string pUserAgentInfo)
        {
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(_ConfigureMock(pUserAgentInfo), OBJECT_ID))
            {
                Assert.AreEqual("window.document.getElementById(\"" + OBJECT_ID + "\").", javaSelenium.JSPrefix);
            }
        }

        private void _GenericFunction1(string pUserAgentInfo)
        {
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(_ConfigureMock(pUserAgentInfo), OBJECT_ID))
            {
                string value = javaSelenium.CreateJSForMethodInvocation("functionName");
                Assert.AreEqual("window.document.getElementById(\"" + OBJECT_ID + "\").functionName();", value);
            }
        }

        private void _GenericFunction2(string pUserAgentInfo)
        {
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(_ConfigureMock(pUserAgentInfo), OBJECT_ID))
            {
                string value = javaSelenium.CreateJSForMethodInvocation("functionName", "param1");
                Assert.AreEqual("window.document.getElementById(\"" + OBJECT_ID + "\").functionName(\"param1\");", value);
            }
        }

        private void _GenericFunction3(string pUserAgentInfo)
        {
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(_ConfigureMock(pUserAgentInfo), OBJECT_ID))
            {
                string value = javaSelenium.CreateJSForMethodInvocation("functionName", "param1", "param2");
                Assert.AreEqual("window.document.getElementById(\"" + OBJECT_ID + "\").functionName(\"param1\",\"param2\");", value);
            }
        }

        private void _GenericGetField1(string pUserAgentInfo)
        {
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(_ConfigureMock(pUserAgentInfo), OBJECT_ID))
            {
                string value = javaSelenium.CreateJSForGetField("fieldName");
                Assert.AreEqual("window.document.getElementById(\"" + OBJECT_ID + "\").fieldName;", value);
            }
        }

        private void _GenericSetField1(string pUserAgentInfo)
        {
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(_ConfigureMock(pUserAgentInfo), OBJECT_ID))
            {
                string value = javaSelenium.CreateJSForSetField("fieldName", "stringValue");
                Assert.AreEqual("window.document.getElementById(\"" + OBJECT_ID + "\").fieldName = \"stringValue\";", value);
            }
        }

        private void _GenericSetField2(string pUserAgentInfo)
        {
            using (JavaSeleniumJSTest javaSelenium = new JavaSeleniumJSTest(_ConfigureMock(pUserAgentInfo), OBJECT_ID))
            {
                string value = javaSelenium.CreateJSForSetField("fieldName", 10);
                Assert.AreEqual("window.document.getElementById(\"" + OBJECT_ID + "\").fieldName = 10;", value);
            }
        }

        private ISelenium _ConfigureMock(string pUserAgentInfo)
        {
            _seleniumMock.ExpectAndReturn("GetEval", pUserAgentInfo, new object[] { "navigator.userAgent" });
            return (ISelenium) _seleniumMock.MockInstance;
        }

        #endregion
    }

    /// <summary>
    /// We use this class to test the JavaScript methods used within the JavaSelenium base class
    /// </summary>
    internal class JavaSeleniumJSTest : JavaSelenium.JavaSelenium
    {
        public JavaSeleniumJSTest(ISelenium pSelenium, string pObjectID) : base( pSelenium, pObjectID)
        {}

        public void SetJSPrefix()
        {
            _SetJSPrefix();
        }

        public string CreateJSForMethodInvocation(string pMethodName, params string[] pParameters )
        {
            return _CreateJSForMethodInvocation(pMethodName, pParameters);
        }

        public string CreateJSForGetField(string pFieldName)
        {
            return _CreateJSForGetField(pFieldName);
        }

        public string CreateJSForSetField(string pFieldName, string pValue)
        {
            return _CreateJSForSetField(pFieldName, pValue);
        }

        public string CreateJSForSetField(string pFieldName, int pValue)
        {
            return _CreateJSForSetField(pFieldName, pValue);
        }
    }
}
