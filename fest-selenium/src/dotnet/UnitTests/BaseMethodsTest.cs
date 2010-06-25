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

using NMock;
using NUnit.Framework;
using Selenium;

namespace UnitTests
{
    [TestFixture]
    public class BaseMethodsTest
    {
        private const string OBJECT_ID = "test";

        private JavaSelenium.JavaSelenium _javaSelenium;
        private DynamicMock _mock;

        [SetUp]
        public void SetUp() 
        {
            _mock = new DynamicMock(typeof(ISelenium));

            ISelenium selenium = (ISelenium) _mock.MockInstance;
            _javaSelenium = new JavaSelenium.JavaSelenium(selenium, OBJECT_ID);
        }

        [TearDown]
        public void TearDown()
        {
            _mock.Verify();
        }

        [Test]
        public void CallToProperty()
        {
            
            _mock.ExpectAndReturn("GetEval", "555", new object[] { "PropertyName();" });
            Assert.AreEqual("555", _javaSelenium.InvokeMethod("PropertyName"));
        }

        [Test]
        public void CallToMethodNoReturnNoArguments()
        {
            _mock.Expect("GetEval", new object[] { "MethodWithNoReturnNoArguments();" });
            _javaSelenium.InvokeMethod("MethodWithNoReturnNoArguments");
        }

        [Test]
        public void CallToMethodNoReturnWithArguments()
        {
            _mock.Expect("GetEval",
                        new object[] { "MethodWithNoReturnWithArguments(\"argument1\");" });
            _javaSelenium.InvokeMethod("MethodWithNoReturnWithArguments", "argument1");
        }

        [Test]
        public void CallToMethodWithReturnNoArguments()
        {
            _mock.ExpectAndReturn("GetEval", "returnValue",
                        new object[] { "MethodWithReturnNoArguments();" });
            Assert.AreEqual("returnValue", _javaSelenium.InvokeMethod("MethodWithReturnNoArguments"));
        }

        [Test]
        public void CallToMethodWithReturnWithArguments()
        {
            _mock.ExpectAndReturn("GetEval", "returnValue",
                        new object[] { "MethodWithReturnWithArguments(\"argument1\");" });
            Assert.AreEqual("returnValue", _javaSelenium.InvokeMethod("MethodWithReturnWithArguments", "argument1"));
        }

        [Test]
        public void CallToGetField()
        {
            _mock.ExpectAndReturn("GetEval", "returnValue",
                        new object[] { "Field;" });
            Assert.AreEqual("returnValue", _javaSelenium.GetField("Field"));
        }

        [Test]
        public void CallToSetFieldWithString()
        {
            _mock.Expect("GetEval", new object[] { "Field = \"Value\";" });
            _javaSelenium.SetField("Field", "Value");
        }

        [Test]
        public void CallToSetFieldWithInt()
        {
            _mock.Expect("GetEval", new object[] { "Field = 10;" });
            _javaSelenium.SetField("Field", 10);
        }
    }
}
