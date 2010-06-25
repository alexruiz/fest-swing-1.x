// Date: Sept 29 2009
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

using System;
using JavaSelenium;
using JavaSelenium.Components;
using JavaSelenium.Components.Interfaces;
using Rhino.Mocks;
using NUnit.Framework;

namespace UnitTests
{
    [TestFixture]
    public class JavaActionTest
    {
        private MockRepository _repository;
        private IJavaSelenium _mockSelenium;
        private JavaAction _action;

        [SetUp]
        public void SetUp()
        {
            _repository = new MockRepository();
            _mockSelenium = _repository.Stub<IJavaSelenium>();
            _action = new JavaAction(_mockSelenium);
        }

        [TearDown]
        public void TearDown()
        {}

        #region Fluent Interface tests

        #region AltKeyDown

        [Test]
        [Category("AltKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyValueAltKeyDown()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.AltKeyDown(button));
        }

        [Test]
        [Category("AltKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringAltKeyDown()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.AltKeyDown(button));
        }

        [Test]
        [Category("AltKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionAltKeyDown()
        {
            Button box = new Button("button");
            Assert.AreEqual(_action, _action.AltKeyDown(box));

            Assert.AreEqual(_action, _action.AltKeyDown<NullComponent>("option"));
        }

        [Test]
        [Category("AltKeyDown")]
        public void TestAltKeyDown()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.AltKeyDown(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK");
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.AltKeyDown(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.AltKeyDown(anotherBox));
            Assert.AreEqual(_action, _action.AltKeyDown<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().AltKeyDown(anotherBox));
        }

        [Test]
        [Category("AltKeyDown")]
        public void TestRootComponentParameterAltKeyDown()
        {
            Button button = new Button("button").Value("c");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).AltKeyDown(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region AltKeyUp

        [Test]
        [Category("AltKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyValueAltKeyUp()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.AltKeyUp(button));
        }

        [Test]
        [Category("AltKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringAltKeyUp()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.AltKeyUp(button));
        }

        [Test]
        [Category("AltKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionAltKeyUp()
        {
            Button box = new Button("button");
            Assert.AreEqual(_action, _action.AltKeyUp(box));

            Assert.AreEqual(_action, _action.AltKeyUp<NullComponent>("option"));
        }

        [Test]
        [Category("AltKeyUp")]
        public void TestAltKeyUp()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.AltKeyUp(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.AltKeyUp(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.AltKeyUp(anotherBox));
            Assert.AreEqual(_action, _action.AltKeyUp<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().AltKeyUp(anotherBox));
        }

        [Test]
        [Category("AltKeyUp")]
        public void TestRootComponentParameterAltKeyUp()
        {
            Button button = new Button("button").Value("c");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).AltKeyUp(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region ControlKeyDown

        [Test]
        [Category("ControlKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyValueControlKeyDown()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.ControlKeyDown(button));
        }

        [Test]
        [Category("ControlKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringControlKeyDown()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.ControlKeyDown(button));
        }

        [Test]
        [Category("ControlKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionControlKeyDown()
        {
            Button box = new Button("button");
            Assert.AreEqual(_action, _action.ControlKeyDown(box));

            Assert.AreEqual(_action, _action.ControlKeyDown<NullComponent>("option"));
        }

        [Test]
        [Category("ControlKeyDown")]
        public void TestControlKeyDown()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.ControlKeyDown(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.ControlKeyDown(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();
            
            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.ControlKeyDown(anotherBox));
            Assert.AreEqual(_action, _action.ControlKeyDown<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().ControlKeyDown(anotherBox));
        }

        [Test]
        [Category("ControlKeyDown")]
        public void TestRootComponentParameterControlKeyDown()
        {
            Button button = new Button("button").Value("c");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).ControlKeyDown(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region ControlKeyUp

        [Test]
        [Category("ControlKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyValueControlKeyUp()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.ControlKeyUp(button));
        }

        [Test]
        [Category("ControlKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringControlKeyUp()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.ControlKeyUp(button));
        }

        [Test]
        [Category("ControlKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionControlKeyUp()
        {
            Button box = new Button("button");
            Assert.AreEqual(_action, _action.ControlKeyUp(box));

            Assert.AreEqual(_action, _action.ControlKeyUp<NullComponent>("option"));
        }

        [Test]
        [Category("ControlKeyUp")]
        public void TestControlKeyUp()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.ControlKeyDown(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.ControlKeyUp(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.ControlKeyUp(anotherBox));
            Assert.AreEqual(_action, _action.ControlKeyUp<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().ControlKeyUp(anotherBox));
        }

        [Test]
        [Category("ControlKeyUp")]
        public void TestRootComponentParameterControlKeyUp()
        {
            Button button = new Button("button").Value("c");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).ControlKeyUp(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region AddSelection

        [Test]
        [Category("AddSelection")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionAddSelection()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(_action, _action.AddSelection(box));

            Assert.AreEqual(_action, _action.AddSelection<NullComponent>("option"));
        }

        [Test]
        [Category("AddSelection")]
        public void TestAddSelection()
        {
            // NOTE: This will NOT WORK as the NullComponent does implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.AddSelection(component));

            List list = new List("list");

            Assert.AreEqual(list, list.AddItem("item"));

            // No longer can AddSelection without specifying an item.
            Assert.AreEqual(_action, _action.AddSelection(list));
            Assert.AreEqual(_action.CurrentComponent, list);

            List anotherList = new List("anotherList").AddItem("item1");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("select")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            // NOTE: Need to fix this mock so that the method arguments are tested as well
            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("select"))).Return("OK");

            Assert.AreEqual("OK", _action.AddSelection(anotherList).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.AddSelection(anotherList));
            Assert.AreEqual(_action, _action.AddSelection<List>("anotherOption"));
            Assert.AreEqual(_action.CurrentComponent, anotherList);
            Assert.AreEqual(2, anotherList.SelectedItems().Count);
        }

        [Test]
        [Category("AddSelection")]
        public void TestRootComponentParameterAddSelection()
        {
            List list = new List("list").AddItem("item");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("select")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("select"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).AddSelection(list));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region Check/Uncheck

        [Test]
        [Category("Check/Uncheck")]
        public void TestCheckUncheck()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".check"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.AtLeastOnce();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".uncheck"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.AtLeastOnce();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".check"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.Check(button).Result);

            Assert.AreEqual(_action, _action.Check(button));
            Assert.AreEqual(_action.CurrentComponent, button);
            Assert.AreEqual(_action, _action.Uncheck(button));
            Assert.AreEqual(_action.CurrentComponent, button);

            Button anotherButton = new Button("anotherButton");

            Assert.AreEqual("OK", _action.Uncheck(anotherButton).Result);

            Assert.AreEqual(_action, _action.Focus(anotherButton).Check());
            Assert.AreEqual(_action.CurrentComponent, anotherButton);
            Assert.AreEqual(_action, _action.Focus(anotherButton).Uncheck());
            Assert.AreEqual(_action.CurrentComponent, anotherButton);

            _repository.VerifyAll();
        }

        [Test]
        [Category("Check/Uncheck")]
        public void TestRootComponentParameterCheckUncheck()
        {
            List list = new List("list").AddItem("item");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".check"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".check"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".uncheck"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".uncheck"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Check(list).Uncheck(list));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region Focus

        [Test]
        [Category("Focus")]
        public void TestFocus()
        {
            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.Focus(table).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.Focus(table));
            Assert.AreEqual(_action.CurrentComponent, table);

            Table anotherTable = new Table("anotherTable");
            Assert.AreEqual(_action, _action.Focus(anotherTable).Focus());
            Assert.AreEqual(_action.CurrentComponent, anotherTable);
        }

        [Test]
        [Category("Focus")]
        public void TestRootComponentParameterFocuse()
        {
            Panel panel = new Panel("panel");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
            //.Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Focus(panel));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region Click

        [Test]
        [Category("Click")]
        public void TestClick()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".click"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".click"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.Click(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.Click(button));
            Assert.AreEqual(_action.CurrentComponent, button);

            Button anotherButton = new Button("anotherButton");
            Assert.AreEqual(_action, _action.Click(button).Click(anotherButton));
            Assert.AreEqual(_action.CurrentComponent, anotherButton);
        }

        [Test]
        [Category("Click")]
        public void TestRootComponentParameterClick()
        {
            Panel panel = new Panel("panel");
            Dialog dialog = new Dialog("rootComponent");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Click(panel));

            //_repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region ClickAt

        [Test]
        [Category("ClickAt")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionClickAt()
        {
            Table table = new Table("table");
            Assert.AreEqual(_action, _action.ClickAt(table));

            Assert.AreEqual(_action, _action.ClickAt<NullComponent>(new Coordinates(0,0)));
        }

        [Test]
        [Category("ClickAt")]
        public void TestClickAt()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasCoordinates.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.ClickAt(button));

            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createPoint")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createPoint"))).Return("OK");

            Assert.AreEqual("OK", _action.ClickAt(table).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.ClickAt(table));
            Assert.AreEqual(_action.CurrentComponent, table);

            Table anotherTable = new Table("anotherTable").Coordinates(new Coordinates(10,10));
            Assert.AreEqual(_action, _action.ClickAt(table).ClickAt(anotherTable));
            Assert.AreEqual(_action, _action.ClickAt<Table>(new Coordinates(0,0)));
            Assert.AreEqual(_action.CurrentComponent, anotherTable);
            Assert.IsTrue(anotherTable.Coordinates().Equals(new Coordinates(0,0)));
        }

        [Test]
        [Category("ClickAt")]
        public void TestRootComponentParameterClickAt()
        {
            Panel panel = new Panel("panel").Coordinates(new Coordinates(20,20));
            Dialog dialog = new Dialog("rootComponent");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).ClickAt(panel));

            //_repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region DoubleClick

        [Test]
        [Category("DoubleClick")]
        public void TestDoubleClick()
        {
            Tree tree = new Tree("tree");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".doubleClick"),
                                                   Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".doubleClick"),
            //                                       Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.DoubleClick(tree).Result);

            _repository.VerifyAll();
            
            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.DoubleClick(tree));
            Assert.AreEqual(_action.CurrentComponent, tree);

            Tree anotherTree = new Tree("anotherTree");
            Assert.AreEqual(_action, _action.DoubleClick(tree).DoubleClick(anotherTree));
            Assert.AreEqual(_action.CurrentComponent, anotherTree);
        }

        [Test]
        [Category("DoubleClick")]
        public void TestRootComponentParameterDoubleClick()
        {
            Panel panel = new Panel("panel");
            Dialog dialog = new Dialog("rootComponent");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).DoubleClick(panel));

            //_repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region DoubleClickAt

        [Test]
        [Category("DoubleClickAt")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionDoubleClickAt()
        {
            Table table = new Table("table");
            Assert.AreEqual(_action, _action.DoubleClickAt(table));

            Assert.AreEqual(_action, _action.DoubleClickAt<NullComponent>(new Coordinates(0,0)));
        }

        [Test]
        [Category("DoubleClickAt")]
        public void TestDoubleClickAt()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasCoordinates.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.DoubleClickAt(button));

            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createPoint")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createPoint"))).Return("OK");

            Assert.AreEqual("OK", _action.DoubleClickAt(table).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.DoubleClickAt(table));
            Assert.AreEqual(_action.CurrentComponent, table);

            Table anotherTable = new Table("anotherTable").Coordinates(new Coordinates(10,10));
            Assert.AreEqual(_action, _action.DoubleClick(table).DoubleClick(anotherTable));
            Assert.AreEqual(_action, _action.DoubleClickAt<Table>(new Coordinates(0,0)));
            Assert.AreEqual(_action.CurrentComponent, anotherTable);
            Assert.IsTrue(anotherTable.Coordinates().Equals(new Coordinates(0,0)));
        }

        [Test]
        [Category("DoubleClickAt")]
        public void TestRootComponentParameterDoubleClickAt()
        {
            Panel panel = new Panel("panel").Coordinates(new Coordinates(20, 20));
            Dialog dialog = new Dialog("rootComponent");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).DoubleClickAt(panel));

            //_repository.VerifyAll();
            
            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region GetSelectedIndex

        [Test]
        [Category("GetSelectedIndex")]
        public void TestGetSelectedIndex()
        {
            ComboBox box = new ComboBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".getSelectedIndex"),
                                                   Arg<string>.Is.Anything))
                .Return("2")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".getSelectedIndex"),
            //                                       Arg<string>.Is.Anything))
            //    .Return("2");

            Assert.AreEqual(2, _action.GetSelectedIndex(box));

            _repository.VerifyAll();
            
            //_mockSelenium.VerifyAllExpectations();

            Assert.IsNotNull(_action.GetSelectedIndex(box));
            Assert.AreEqual(_action.CurrentComponent, box);
            
            ComboBox anotherBox = new ComboBox("anotherBox");
            Assert.IsNotNull(_action.Focus(anotherBox).GetSelectedIndex());
            Assert.AreEqual(_action.CurrentComponent, anotherBox);            
        }

        [Test]
        [Category("GetSelectedIndex")]
        public void TestRootComponentParameterGetSelectedIndex()
        {
            ComboBox box = new ComboBox("box");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".getSelectedIndex"),
                                                   Arg<string>.Is.Anything))
                .Return("2")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".getSelectedIndex"),
            //                           Arg<string>.Is.Anything))
            //.Return("2");

            Assert.AreEqual(2, _action.SetRootComponent(dialog).GetSelectedIndex(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region GetSelectedIndices

        [Test]
        [Category("GetSelectedIndices")]
        public void TestGetSelectedIndices()
        {
            List list = new List("list");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything))
                .Return("1,2,3")
                .Repeat.AtLeastOnce();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything)).Return("1,2,3");

            Assert.AreEqual(3, _action.GetSelectedIndices(list).Count);
            Assert.AreEqual(1, _action.GetSelectedIndices(list)[0]);
            Assert.AreEqual(2, _action.GetSelectedIndices(list)[1]);
            Assert.AreEqual(3, _action.GetSelectedIndices(list)[2]);

            _repository.VerifyAll();

            // _mockSelenium.VerifyAllExpectations();
        }

        [Test]
        [Category("GetSelectedIndices")]
        public void TestRootComponentParameterGetSelectedIndices()
        {
            List list = new List("list");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything))
                .Return("1,2,3")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything)).Return("1,2,3");

            Assert.AreEqual(3, _action.SetRootComponent(dialog).GetSelectedIndices(list).Count);

            _repository.VerifyAll();
            
            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region GetSelectedValue

        [Test]
        [Category("GetSelectedValue")]
        public void TestGetSelectedValue()
        {
            ComboBox box = new ComboBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains(".getSelected"),
                                                   Arg<string>.Is.Anything))
                .Return("value")
                .Repeat.AtLeastOnce();
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains(".getSelected"),
            //                                       Arg<string>.Is.Anything))
            //    .Return("value");

            Assert.AreEqual("value", _action.GetSelectedValue(box));
            Assert.IsNotNull(_action.GetSelectedValue(box));
            Assert.AreEqual(_action.CurrentComponent, box);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();


            ComboBox anotherBox = new ComboBox("anotherBox");
            Assert.IsNotNull(_action.Focus(anotherBox).GetSelectedValue());
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
        }

        [Test]
        [Category("GetSelectedValue")]
        public void TestRootComponentParameterGetSelectedValue()
        {
            ComboBox box = new ComboBox("box");
            Dialog dialog = new Dialog("rootComponent");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains(".getSelected"),
                                                   Arg<string>.Is.Anything))
                .Return("value")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains(".getSelected"),
            //                           Arg<string>.Is.Anything))
            //.Return("value");

            Assert.AreEqual("value", _action.SetRootComponent(dialog).GetSelectedValue(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region GetSelectedValues

        [Test]
        [Category("GetSelectedValues")]
        public void TestGetSelectedValues()
        {
            List list = new List("list");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains(".getSelected"),
                                                   Arg<string>.Is.Anything))
                .Return("value1, value2, value3")
                .Repeat.AtLeastOnce();
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything)).Return(
            //    "value1, value2, value3");

            //Assertions
            Assert.AreEqual(3, _action.GetSelectedValues(list).Count);
            Assert.AreEqual("value1", _action.GetSelectedValues(list)[0]);
            Assert.AreEqual("value2", _action.GetSelectedValues(list)[1]);
            Assert.AreEqual("value3", _action.GetSelectedValues(list)[2]);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        [Test]
        [Category("GetSelectedValues")]
        public void TestRootComponentParameterGetSelectedValues()
        {
            Dialog dialog = new Dialog("rootComponent");

            List list = new List("list");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains(".getSelected"),
                                                   Arg<string>.Is.Anything))
                .Return("value1, value2, value3")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything)).Return(
            //    "value1, value2, value3");

            Assert.AreEqual(3, _action.SetRootComponent(dialog).GetSelectedValues(list).Count);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region GetValue

        [Test]
        [Category("GetValue")]
        public void TestGetValue()
        {
            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains("selectionValue"),
                                                   Arg<string>.Is.Anything))
                .Return("value")
                .Repeat.AtLeastOnce();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("selectionValue"),
            //                                       Arg<string>.Is.Anything))
            //    .Return("value");

            Assert.AreEqual("value", _action.GetValue(table));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.IsNotNull(_action.GetValue(table));
            Assert.AreEqual(_action.CurrentComponent, table);
            
            Table anotherTable = new Table("anotherTable");
            Assert.IsNotNull(_action.Focus(anotherTable).GetValue());
            Assert.AreEqual(_action.CurrentComponent, anotherTable);
        }

        [Test]
        [Category("GetValue")]
        public void TestRootComponentParameterGetValue()
        {
            Dialog dialog = new Dialog("rootComponent");
            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains("selectionValue"),
                                                   Arg<string>.Is.Anything))
                .Return("value")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("selectionValue"),
            //                                       Arg<string>.Is.Anything))
            //    .Return("value");

            Assert.AreEqual("value", _action.SetRootComponent(dialog).GetValue(table));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region GetValues

        [Test]
        [Category("GetValues")]
        public void TestGetValues()
        {
            List list = new List("list");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything))
                .Return("value1, value2, value3")
                .Repeat.AtLeastOnce();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything)).Return(
            //    "value1, value2, value3");

            //Assertions
            Assert.AreEqual(3, _action.GetSelectedValues(list).Count);
            Assert.AreEqual("value1", _action.GetSelectedValues(list)[0]);
            Assert.AreEqual("value2", _action.GetSelectedValues(list)[1]);
            Assert.AreEqual("value3", _action.GetSelectedValues(list)[2]);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        [Test]
        [Category("GetValues")]
        public void TestRootComponentParameterGetValues()
        {
            Dialog dialog = new Dialog("rootComponent");

            List list = new List("list");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything))
                .Return("value1, value2, value3")
                .Repeat.Once();
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.Contains("getSelected"), Arg<string>.Is.Anything)).Return(
            //    "value1, value2, value3");

            Assert.AreEqual(3, _action.SetRootComponent(dialog).GetValues(list).Count);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

        }

        #endregion

        #region GetText

        [Test]
        [Category("GetText")]
        public void TestGetText()
        {
            TextBox box = new TextBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".text"),
                                                   Arg<string>.Is.Anything))
                .Return("text")
                .Repeat.AtLeastOnce();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".text"),
            //                                       Arg<string>.Is.Anything))
            //    .Return("text");

            Assert.AreEqual("text", _action.GetText(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.IsNotNull(_action.GetText(box));
            Assert.AreEqual(_action.CurrentComponent, box);

            TextBox anotherBox = new TextBox("anotherBox");
            Assert.IsNotNull(_action.Focus(anotherBox).GetText());
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
        }

        [Test]
        [Category("GetText")]
        public void TestRootComponentParameterGetText()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".text"),
                                                   Arg<string>.Is.Anything))
                .Return("text")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".text"),
            //                                       Arg<string>.Is.Anything))
            //    .Return("text");

            Assert.AreEqual("text", _action.SetRootComponent(dialog).GetText(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region KeyDown

        [Test]
        [Category("KeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyValueKeyDown()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.KeyDown(button));
        }

        [Test]
        [Category("KeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringKeyDown()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.KeyDown(button));
        }

        [Test]
        [Category("KeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionKeyDown()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(_action, _action.KeyDown(box));

            Assert.AreEqual(_action, _action.KeyDown<NullComponent>("value"));
        }

        [Test]
        [Category("KeyDown")]
        public void TestKeyDown()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.KeyDown(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.KeyDown(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.KeyDown(anotherBox));
            Assert.AreEqual(_action, _action.KeyDown<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().KeyDown(anotherBox));
        }

        [Test]
        [Category("KeyDown")]
        public void TestRootComponentParameterKeyDown()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).KeyDown(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region KeyPress

        [Test]
        [Category("KeyPress")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyValueKeyPress()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.KeyPress(button));
        }

        [Test]
        [Category("KeyPress")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringKeyPress()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.KeyPress(button));
        }

        [Test]
        [Category("KeyPress")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionKeyPress()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(_action, _action.KeyPress(box));

            Assert.AreEqual(_action, _action.KeyPress<NullComponent>("value"));
        }

        [Test]        
        [Category("KeyPress")]
        public void TestKeyPress()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.KeyPress(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.KeyPress(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.KeyPress(anotherBox));
            Assert.AreEqual(_action, _action.KeyPress<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().KeyPress(anotherBox));
        }

        [Test]
        [Category("KeyPress")]
        public void TestRootComponentParameterKeyPress()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).KeyPress(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region KeyUp

        [Test]
        [Category("KeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyValueKeyUp()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.KeyUp(button));
        }

        [Test]
        [Category("KeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringKeyUp()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.KeyUp(button));
        }

        [Test]
        [Category("KeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionKeyUp()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(_action, _action.KeyUp(box));

            Assert.AreEqual(_action, _action.KeyUp<NullComponent>("value"));
        }

        [Test]
        [Category("KeyUp")]
        public void TestKeyUp()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.KeyUp(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.KeyUp(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.KeyUp(anotherBox));
            Assert.AreEqual(_action, _action.KeyUp<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().KeyUp(anotherBox));
        }

        [Test]
        [Category("KeyUp")]
        public void TestRootComponentParameterKeyUp()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).KeyUp(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseDown

        [Test]
        [Category("MouseDown")]
        public void TestMouseDown()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createMouseButton")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createMouseButton"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseDown(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseDown());
            Assert.AreEqual(_action, _action.Focus(button).MouseDown());
        }

        [Test]
        [Category("MouseDown")]
        public void TestRootComponentParameterMouseDown()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createMouseButton")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createMouseButton"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).MouseDown(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseDownAt

        [Test]
        [Category("MouseDownAt")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionMouseDownAt()
        {
            Table table = new Table("table");
            Assert.AreEqual(_action, _action.MouseDownAt(table));

            // NOTE : We attempt to use a class of Button here instead of the Table component class
            Assert.AreEqual(_action, _action.MouseDownAt<Button>(new Coordinates(0,0)));
        }

        [Test]
        [Category("MouseDownAt")]
        public void TestMouseDownAt()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasCoordinates.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.DoubleClickAt(button));

            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createPoint")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createPoint"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseDownAt(table).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseDownAt(table));
            Assert.AreEqual(_action.CurrentComponent, table);

            Table anotherTable = new Table("anotherTable").Coordinates(new Coordinates(10,10));
            Assert.AreEqual(_action, _action.MouseDownAt(table).MouseDownAt(anotherTable));
            Assert.AreEqual(_action, _action.MouseDownAt<Table>(new Coordinates(0,0)));
            Assert.AreEqual(_action.CurrentComponent, anotherTable);
            Assert.IsTrue(anotherTable.Coordinates().Equals(new Coordinates(0,0)));
        }

        [Test]
        [Category("MouseDownAt")]
        public void TestRootComponentParameterMouseDownAt()
        {
            Dialog dialog = new Dialog("rootComponent");
            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createPoint")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createPoint"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Focus(table).MouseDownAt<Table>(new Coordinates(10,10)));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseMove

        [Test]
        [Category("MouseMove")]
        public void TestMouseMove()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("moveMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("moveMouse"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseMove(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseMove());
            Assert.AreEqual(_action, _action.Focus(button).MouseMove());

        }

        [Test]
        [Category("MouseMove")]
        public void TestRootComponentParameterMouseMove()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createMouseButton")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createMouseButton"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).MouseMove(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseMoveAt

        [Test]
        [Category("MouseMoveAt")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionMouseMoveAt()
        {
            Table table = new Table("table");
            Assert.AreEqual(_action, _action.MouseMoveAt(table));

            // NOTE : We attempt to use a class of Button here instead of the Table component class
            Assert.AreEqual(_action, _action.MouseMoveAt<Button>(new Coordinates(0,0)));
        }

        [Test]
        [Category("MouseMoveAt")]
        public void TestMouseMoveAt()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasCoordinates.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.DoubleClickAt(button));

            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createPoint")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createPoint"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseMoveAt(table).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseMoveAt(table));
            Assert.AreEqual(_action.CurrentComponent, table);

            Table anotherTable = new Table("anotherTable").Coordinates(new Coordinates(10,10));
            Assert.AreEqual(_action, _action.MouseMoveAt(table).MouseMoveAt(anotherTable));
            Assert.AreEqual(_action, _action.MouseMoveAt<Table>(new Coordinates(0,0)));
            Assert.AreEqual(_action.CurrentComponent, anotherTable);
            Assert.IsTrue(anotherTable.Coordinates().Equals(new Coordinates(0,0)));
        }

        [Test]
        [Category("MouseMoveAt")]
        public void TestRootComponentParameterMouseMoveAt()
        {
            Dialog dialog = new Dialog("rootComponent");
            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("createPoint")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("createPoint"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Focus(table).MouseMoveAt<Table>(new Coordinates(10,10)));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseOut

        [Test]
        [Category("MouseOut")]
        [Ignore("Not currently implemented in FEST")]
        public void TestMouseOut()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains(".robot.moveMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains(".robot.moveMouse"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseOut(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseOut());
            Assert.AreEqual(_action, _action.Focus(button).MouseOut());
        }

        [Test]
        [Category("MouseOut")]
        [Ignore("Not currently implemented in FEST")]
        public void TestRootComponentParameterMouseOut()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains(".robot.moveMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains(".robot.moveMouse"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).MouseOut(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseOver

        [Test]
        [Category("MouseOver")]
        public void TestMouseOver()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("moveMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("moveMouse"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseOver(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseOver());
            Assert.AreEqual(_action, _action.Focus(button).MouseOver());
        }

        [Test]
        [Category("MouseOver")]
        public void TestRootComponentParameterMouseOver()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("moveMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();
            
            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("moveMouse"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).MouseOver(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseUp

        [Test]
        [Category("MouseUp")]
        public void TestMouseUp()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("releaseMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("releaseMouse"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseUp(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseUp());
            Assert.AreEqual(_action, _action.Focus(button).MouseUp());
        }

        [Test]
        [Category("MouseUp")]
        public void TestRootComponentParameterMouseUp()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("releaseMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("releaseMouse"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).MouseUp(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region MouseUpAt

        [Test]
        [Category("MouseUpAt")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionMouseUpAt()
        {
            Table table = new Table("table");
            Assert.AreEqual(_action, _action.MouseUpAt(table));

            // NOTE : We attempt to use a class of Button here instead of the Table component class
            Assert.AreEqual(_action, _action.MouseUpAt<Button>(new Coordinates(0,0)));
        }

        [Test]
        [Category("MouseUpAt")]
        public void TestMouseUpAt()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasCoordinates.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.DoubleClickAt(button));

            Table table = new Table("table");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("releaseMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("releaseMouse"))).Return("OK");

            Assert.AreEqual("OK", _action.MouseUpAt(table).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.MouseUpAt(table));
            Assert.AreEqual(_action.CurrentComponent, table);

            Table anotherTable = new Table("anotherTable").Coordinates(new Coordinates(10,10));
            Assert.AreEqual(_action, _action.MouseUpAt(table).MouseUpAt(anotherTable));
            Assert.AreEqual(_action, _action.MouseUpAt<Table>(new Coordinates(0,0)));
            Assert.AreEqual(_action.CurrentComponent, anotherTable);
            Assert.IsTrue(anotherTable.Coordinates().Equals(new Coordinates(0, 0)));
        }

        [Test]
        [Category("MouseUpAt")]
        public void TestRootComponentParameterMouseUpAt()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("releaseMouse")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("releaseMouse"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Focus(button).MouseUpAt(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region Select

        [Test]
        [Category("Select")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionSelect()
        {
            TestExceptionAddSelection();
        }

        [Test]
        [Category("Select")]
        public void TestSelect()
        {
            TestAddSelection();
        }

        [Test]
        [Category("Select")]
        public void TestRootComponentParameterSelect()
        {
            TestRootComponentParameterAddSelection();
        }

        #endregion

        #region SelectFrame

        [Test]
        [Category("SelectFrame")]
        public void TestSelectFrame()
        {
            TextBox box = new TextBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.SelectFrame(box).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.SelectFrame(box));
            Assert.AreEqual(_action.CurrentComponent, box);

            TextBox anotherBox = new TextBox("anotherBox");
            Assert.AreEqual(_action, _action.Focus(anotherBox).SelectFrame());
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
        }

        [Test]
        [Category("SelectFrame")]
        public void TestRootComponentParameterSelectFrame()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).SelectFrame(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region SelectWindow

        [Test]
        [Category("SelectWindow")]
        public void TestSelectWindow()
        {
            TextBox box = new TextBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.SelectWindow(box).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.SelectWindow(box));
            Assert.AreEqual(_action.CurrentComponent, box);

            TextBox anotherBox = new TextBox("anotherBox");
            Assert.AreEqual(_action, _action.Focus(anotherBox).SelectWindow());
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
        }

        [Test]
        [Category("SelectWindow")]
        public void TestRootComponentParameterSelectWindow()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".focus"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).SelectWindow(box));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region SelectPopUp

        [Test]
        [Category("SelectPopUp")]
        public void TestSelectPopUp()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".showPopupMenu"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".showPopupMenu"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.SelectPopUp(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.SelectPopUp(button));
            Assert.AreEqual(_action.CurrentComponent, button);

            Button anotherButton = new Button("anotherButton");
            Assert.AreEqual(_action, _action.Focus(anotherButton).SelectPopUp());
            Assert.AreEqual(_action.CurrentComponent, anotherButton);
        }

        [Test]
        [Category("SelectPopUp")]
        public void TestRootComponentParameterSelectPopUp()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".showPopupMenu"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".showPopupMenu"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).SelectPopUp(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region RightClick

        [Test]
        [Category("RightClick")]
        public void TestRightClick()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".rightClick"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".rightClick"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.RightClick(button).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.RightClick());
            Assert.AreEqual(_action, _action.Focus(button).RightClick());
        }

        [Test]
        [Category("RightClick")]
        public void TestRootComponentParameterRightClick()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".rightClick"), Arg<string>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".rightClick"), Arg<string>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).RightClick(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region IsEnabled

        [Test]
        [Category("IsEnabled")]
        public void TestIsEnabled()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".component().isEnabled"), Arg<string>.Is.Anything))
                .Return("true")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".component().isEnabled"), Arg<string>.Is.Anything))
            //    .Return("true");

            Assert.AreEqual(true, _action.IsEnabled(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action.CurrentComponent, button);
        }

        [Test]
        [Category("IsEnabled")]
        public void TestRootComponentParameterIsEnabled()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".component().isEnabled"), Arg<string>.Is.Anything))
                .Return("true")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".component().isEnabled"), Arg<string>.Is.Anything))
            //    .Return("true");

            Assert.AreEqual(true, _action.SetRootComponent(dialog).IsEnabled(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region IsVisible

        [Test]
        [Category("IsVisible")]
        public void TestIsVisible()
        {
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".component().isVisible"), Arg<string>.Is.Anything))
                .Return("true")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".component().isVisible"), Arg<string>.Is.Anything))
            //    .Return("true");

            Assert.AreEqual(true, _action.IsVisible(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action.CurrentComponent, button);
        }

        [Test]
        [Category("IsVisible")]
        public void TestRootComponentParameterIsVisible()
        {
            Dialog dialog = new Dialog("rootComponent");
            Button button = new Button("button");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".component().isVisible"), Arg<string>.Is.Anything))
                .Return("true")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".component().isVisible"), Arg<string>.Is.Anything))
            //    .Return("true");

            Assert.AreEqual(true, _action.SetRootComponent(dialog).IsVisible(button));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region ShiftKeyDown

        [Test]
        [Category("ShiftKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyShiftKeyDown()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.ShiftKeyDown(button));
        }

        [Test]
        [Category("ShiftKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringShiftKeyDown()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.ShiftKeyDown(button));
        }

        [Test]
        [Category("ShiftKeyDown")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionShiftKeyDown()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(_action, _action.ShiftKeyDown(box));

            Assert.AreEqual(_action, _action.ShiftKeyDown<NullComponent>("value"));
        }

        [Test]
        [Category("ShiftKeyDown")]
        public void TestShiftKeyDown()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.ShiftKeyDown(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.ShiftKeyDown(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.ShiftKeyDown(anotherBox));
            Assert.AreEqual(_action, _action.ShiftKeyDown<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().ShiftKeyDown(anotherBox));
        }

        [Test]
        [Category("ShiftKeyDown")]
        public void TestRootComponentParameterShiftKeyDown()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Focus(box).ShiftKeyDown<TextBox>("B"));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region ShiftKeyUp

        [Test]
        [Category("ShiftKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyShiftKeyUp()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.ShiftKeyUp(button));
        }

        [Test]
        [Category("ShiftKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionLargeStringShiftKeyUp()
        {
            Button button = new Button("button").Value("abc"); // Only really need a single character for the command
            Assert.AreEqual(_action, _action.ShiftKeyUp(button));
        }

        [Test]
        [Category("ShiftKeyUp")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionShiftKeyUp()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(_action, _action.ShiftKeyUp(box));

            Assert.AreEqual(_action, _action.ShiftKeyUp<NullComponent>("value"));
        }

        [Test]
        [Category("ShiftKeyUp")]
        public void TestShiftKeyUp()
        {
            // NOTE: This will NOT WORK with any component that does NOT implement IHasOption.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //NullComponent component = new NullComponent("null");
            //Assert.AreEqual(_action, _action.ShiftKeyUp(component));

            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual("OK", _action.ShiftKeyUp(box).Result);
            Assert.AreEqual(box, _action.CurrentComponent);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            TextBox anotherBox = new TextBox("anotherBox").Value("!");

            Assert.AreEqual(_action, _action.ShiftKeyUp(anotherBox));
            Assert.AreEqual(_action, _action.ShiftKeyUp<TextBox>("2"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("2", anotherBox.Value());

            Assert.AreEqual(_action, _action.Focus().ShiftKeyUp(anotherBox));
        }

        [Test]
        [Category("ShiftKeyUp")]
        public void TestRootComponentParameterShiftKeyUp()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box").Value("A");

            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.EndsWith(".pressAndReleaseKey(javaObject);"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Focus(box).ShiftKeyUp<TextBox>("B"));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region Type

        [Test]
        [Category("Type")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionEmptyType()
        {
            Button button = new Button("button"); // No .Value() set
            Assert.AreEqual(_action, _action.Type(button));
        }

        [Test]
        [Category("Type")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionType()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(_action, _action.Type(box));

            // NOTE : We attempt to use a class of Button here instead of the TextBox component class
            Assert.AreEqual(_action, _action.Type<Button>("some other text."));
        }

        [Test]
        [Category("Type")]
        public void TestType()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasInputValue.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.Type(button));

            TextBox box = new TextBox("box").Value("some text");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".enterText"),
                                                   Arg<object>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".enterText"),
            //                                       Arg<object>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual("OK", _action.Type(box).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.Type(box));
            Assert.AreEqual(_action.CurrentComponent, box);

            TextBox anotherBox = new TextBox("anotherBox").Value("some text");
            Assert.AreEqual(_action, _action.Type(box).Type(anotherBox));
            Assert.AreEqual(_action, _action.Type<TextBox>("some other text"));
            Assert.AreEqual(_action.CurrentComponent, anotherBox);
            Assert.AreEqual("some other text", anotherBox.Value());
        }

        [Test]
        [Category("Type")]
        public void TestRootComponentParameterType()
        {
            Dialog dialog = new Dialog("rootComponent");
            TextBox box = new TextBox("box").Value("some text");

            _repository.Record();
            Expect.Call(_mockSelenium.InvokeMethod(Arg.Text.EndsWith(".enterText"),
                                                   Arg<object>.Is.Anything))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.InvokeMethod(Arg.Text.EndsWith(".enterText"),
            //                                       Arg<object>.Is.Anything))
            //    .Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).Focus(box).Type<TextBox>("some other text"));

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        // See ISeleniumOverrides
        /*
        #region WaitForPopUp

        [Test]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionsWaitForPopUp()
        {
            Tree tree = new Tree("tree").TimeOut("200");

            _mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains(".waitForPopUp"))).Return("OK");

            Assert.AreEqual(_action, _action.WaitForPopUp(tree));
            Assert.AreEqual("OK", _action.Result);

            // NOTE : We attempt to use a class of Button here instead of the Tree component class
            Assert.AreEqual(_action, _action.WaitForPopUp<Button>("some other text."));
        }

        [Test]
        public void TestWaitForPopUp()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasTimeOut.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.WaitForPopUp(button));

            Tree tree = new Tree("tree");

            _mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains(".waitForPopUp"))).Return("OK");

            Assert.AreEqual("OK", _action.WaitForPopUp(tree).Result);

            _mockSelenium.VerifyAllExpectations();

            Assert.AreEqual(_action, _action.WaitForPopUp(tree));
            Assert.AreEqual(_action.CurrentComponent, tree);

            Tree anotherTree = new Tree("anotherTree").TimeOut("100");
            Assert.AreEqual(_action, _action.Focus().WaitForPopUp(anotherTree));
            Assert.AreEqual(_action, _action.WaitForPopUp<Tree>("300"));
            Assert.AreEqual(_action.CurrentComponent, anotherTree);
            Assert.AreEqual("300", anotherTree.TimeOut());
            Assert.AreEqual(_action, _action.WaitForPopUp());
        }

        [Test]
        [Category("WaitForPopUp")]
        public void TestRootComponentParameterWaitForPopUp()
        {
            Dialog dialog = new Dialog("rootComponent");
            Tree tree = new Tree("tree");

            _mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains(".waitForPopUp"))).Return("OK");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).WaitForPopUp(tree));            
        }

        #endregion
        */

        #region WaitForNotBusy

        [Test]
        [Category("WaitForNotBusy")]
        [ExpectedException(typeof(ArgumentException))]
        public void TestExceptionWaitForNotBusy()
        {
            JavaAction.WaitForNotBusyDelegate pDelegate = _WaitForNotBusyImplementation;

            Tree tree = new Tree("tree");

            Assert.AreEqual(_action, _action.WaitForNotBusy(tree, pDelegate));
            Assert.AreEqual("OK", _action.Result);

            // NOTE : We attempt to use a class of Button here instead of the Tree component class
            Assert.AreEqual(_action, _action.WaitForNotBusy<Button>("some other text.", pDelegate));
        }

        [Test]
        [Category("WaitForNotBusy")]
        public void TestWaitForNotBusy()
        {
            // NOTE: This will NOT WORK as the Button Component does implement IHasTimeOut.
            // This constraint will generate compile time errors if a Component is used that
            // does not implement the required interface.

            //Button button = new Button("button");
            //Assert.AreEqual(_action, _action.WaitForNotBusy(button));

            JavaAction.WaitForNotBusyDelegate pDelegate = _WaitForNotBusyImplementation;

            Tree tree = new Tree("tree");

            Assert.AreEqual("OK", _action.WaitForNotBusy(tree, pDelegate).Result);

            Assert.AreEqual(_action, _action.WaitForNotBusy(tree, pDelegate));
            Assert.AreEqual(_action.CurrentComponent, tree);

            Tree anotherTree = new Tree("anotherTree").TimeOut("200");
            Assert.AreEqual(_action, _action.Focus().WaitForNotBusy(anotherTree, pDelegate));
            Assert.AreEqual(_action, _action.WaitForNotBusy<Tree>("500", pDelegate));
            Assert.AreEqual(_action.CurrentComponent, anotherTree);
            Assert.AreEqual("500", anotherTree.TimeOut());
            Assert.AreEqual(_action, _action.WaitForNotBusy(pDelegate));
        }

        [Test]
        [Category("WaitForNotBusy")]
        public void TestRootComponentParameterWaitForNotBusy()
        {
            JavaAction.WaitForNotBusyDelegate pDelegate = _WaitForNotBusyImplementation;

            Dialog dialog = new Dialog("rootComponent");
            Tree tree = new Tree("tree");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog).WaitForNotBusy(tree, pDelegate));  
        }

        #endregion

        [Test]
        public void TestScrollBrowserWindowToTop()
        {
            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("window.scroll")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("window.scroll"))).Return("OK");

            Assert.AreEqual("OK", _action.ScrollBrowserWindowToBottom().Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        [Test]
        public void TestScrollBrowserWindowToBottom()
        {
            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("var height")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("var height"))).Return("OK");

            Assert.AreEqual("OK", _action.ScrollBrowserWindowToBottom().Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        [Test]
        public void TestScrollBrowserWindowTo()
        {
            _repository.Record();
            Expect.Call(_mockSelenium.GetEval(Arg.Text.Contains("window.scroll")))
                .Return("OK")
                .Repeat.Once();
            _repository.ReplayAll();

            //_mockSelenium.Stub(x => x.GetEval(Arg.Text.Contains("window.scroll"))).Return("OK");

            Assert.AreEqual("OK", _action.ScrollBrowserWindowTo(100, 200).Result);

            _repository.VerifyAll();

            //_mockSelenium.VerifyAllExpectations();
        }

        #endregion

        #region IRootComponentAware

        [Test]
        [Category("IRootComponentAware")]
        public void TestRootComponentsProperty()
        {
            Dialog dialog1 = new Dialog("dialog1");
            Dialog dialog2 = new Dialog("dialog2");

            Assert.IsNotNull(_action.RootComponents);
            Assert.AreEqual(0, _action.RootComponents.Length);

            _action.RootComponents = new IRootComponent[] {dialog1, dialog2};

            Assert.AreEqual(2, _action.RootComponents.Length);
            Assert.AreEqual(dialog1, _action.RootComponents[0]);
            Assert.AreEqual(dialog2, _action.RootComponents[1]);
        }

        [Test]
        [Category("IRootComponentAware")]
        public void TestGetRootComponents()
        {
            Dialog dialog1 = new Dialog("dialog1");
            Dialog dialog2 = new Dialog("dialog2");

            _action.RootComponents = new IRootComponent[] {dialog1, dialog2};

            Assert.IsNotNull(_action.GetRootComponents());
            Assert.AreEqual(2, _action.GetRootComponents().Count);
            Assert.AreEqual(dialog1, _action.GetRootComponents()[0]);
            Assert.AreEqual(dialog2, _action.GetRootComponents()[1]);
        }

        [Test]
        [Category("IRootComponentAware")]
        public void TestSetDefaultRootComponent()
        {
            Dialog dialog1 = new Dialog("dialog1");
            Dialog dialog2 = new Dialog("dialog2");

            _action.RootComponents = new IRootComponent[] { dialog1, dialog2 };

            Assert.IsNotNull(_action.GetRootComponents());
            Assert.AreEqual(2, _action.GetRootComponents().Count);

            Assert.AreEqual(_action, _action.SetDefaultRootComponent());
            Assert.AreEqual(0, _action.GetRootComponents().Count);
        }

        [Test]
        [Category("IRootComponentAware")]
        public void TestSetRootComponent()
        {
            Dialog dialog1 = new Dialog("dialog1");
            Dialog dialog2 = new Dialog("dialog2");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog1, dialog2));
            Assert.AreEqual(2, _action.GetRootComponents().Count);
            Assert.AreEqual(dialog1, _action.GetRootComponents()[0]);
            Assert.AreEqual(dialog2, _action.GetRootComponents()[1]);
        }

        [Test]
        [Category("IRootComponentAware")]
        public void TestGetRootComponentPrefix()
        {
            Dialog dialog1 = new Dialog("dialog1");
            Dialog dialog2 = new Dialog("dialog2");

            Assert.AreEqual(_action, _action.SetRootComponent(dialog1, dialog2));
            Assert.AreEqual("dialog(\"dialog1\").dialog(\"dialog2\")", _action.GetRootComponentPrefix());
        }

        #endregion


        #region Delegate Implementation

        private static string _WaitForNotBusyImplementation()
        {
            return "OK";
        }
        
        #endregion
    }
}
