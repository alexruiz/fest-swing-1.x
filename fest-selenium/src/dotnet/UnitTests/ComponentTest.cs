// Date: Oct 20 2009
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
using NUnit.Framework;

namespace UnitTests
{
    [TestFixture]
    public class ComponentTest
    {
        [SetUp]
        public void Setup()
        {}

        [TearDown]
        public void TearDown()
        {}

        #region Button

        [Test]
        [Category("Button")]
        public void TestButton()
        {
            Button button = new Button("newButton");
            
            Assert.AreEqual("newButton", button.Name);
            Assert.AreEqual(String.Empty, button.Text);
            Assert.IsTrue(button is Component);

            button.Text = "newText";

            Button anotherButton = new Button("anotherButton", "someText");
            Assert.AreEqual("someText", anotherButton.Text);
            Assert.AreEqual("anotherButton", anotherButton.Name);

            Assert.AreEqual("button", button.Type);
            Assert.AreEqual("button(\"anotherButton\")", anotherButton.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().button(\"anotherButton\")", anotherButton.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").button(\"anotherButton\")", anotherButton.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region ComboBox

        [Test]
        [Category("ComboBox")]
        public void TestComboBox()
        {
            ComboBox box = new ComboBox("newBox");

            Assert.AreEqual("newBox", box.Name);
            Assert.AreEqual(String.Empty, box.Text);
            Assert.IsTrue(box is Component);

            ComboBox anotherBox = new ComboBox("anotherBox", "someText");
            Assert.AreEqual("someText", anotherBox.Text);
            Assert.AreEqual("anotherBox", anotherBox.Name);

            Assert.AreEqual("comboBox", anotherBox.Type);
            Assert.AreEqual("comboBox(\"anotherBox\")", anotherBox.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().comboBox(\"anotherBox\")", anotherBox.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").comboBox(\"anotherBox\")", anotherBox.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxSelectedItem()
        {
            ComboBox box = new ComboBox("box").AddItem("item1");
            Assert.AreEqual("item1", box.SelectedItem());
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxSelectedItems()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(box, box.AddItem("item1"));
            Assert.AreEqual("item1", box.SelectedItems()[0]);
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxAddItem()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(box, box.AddItem("item1"));
            Assert.AreEqual(box, box.AddItem("item1").AddItem("item2"));
            Assert.AreEqual("item2", box.SelectedItem());
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use AddItem()")]
        public void TestComboBoxAddItemsException()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(box, box.AddItems(new object[] { "item1", "item2" }));
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxAddItems()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(box, box.AddItems(new object[] { "item1" }));
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxRemoveItem()
        {
            ComboBox box = new ComboBox("box").AddItem("some text");
            Assert.AreEqual(box, box.RemoveItem("some text"));
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use RemoveItem().")]
        public void TestComboBoxRemoveItems()
        {
            ComboBox box = new ComboBox("box").RemoveItems(new object[] { "item1" });
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxSelectedItemEvalMethod()
        {
            ComboBox box = new ComboBox("box").AddItem("selectedText");
            Assert.AreEqual("query_base.selectItem(\"selectedText\");", box.SelectedItemEvalMethod("query_base"));
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxSelectedItemsEvalMethod()
        {
            ComboBox box = new ComboBox("box").AddItem("selectedText");
            Assert.AreEqual("query_base.selectItem(\"selectedText\");", box.SelectedItemsEvalMethod("query_base"));
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "Please supply a valid script argument.")]
        public void TestComboBoxSelectedItemEvalMethodNoArguments()
        {
            ComboBox box = new ComboBox("box").AddItem("selectedText");
            box.SelectedItemEvalMethod();
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "Too many script arguments. Only one is required.")]
        public void TestComboBoxSelectedItemEvalMethodTooManyArguments()
        {
            ComboBox box = new ComboBox("box").AddItem("selectedText");
            box.SelectedItemEvalMethod("base_query", "component");
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are strings.")]
        public void TestComboBoxValidate()
        {
            ComboBox box = new ComboBox("box").AddItem(2);
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestComboBoxSelectedItemException()
        {
            ComboBox box = new ComboBox("box");
            box.SelectedItem();
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestComboBoxSelectedItemsException()
        {
            ComboBox box = new ComboBox("box");
            box.SelectedItems();
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TextComboBoxSelectedItemEvalMethodException()
        {
            ComboBox box = new ComboBox("box");
            box.SelectedItemEvalMethod();
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestComboBoxSelectedItemsEvalMethodException()
        {
            ComboBox box = new ComboBox("box");
            box.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestComboBoxRemoveNonExistentItem()
        {
            ComboBox box = new ComboBox("box").RemoveItem("box");
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxGetSelectedRowQuery()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(box.GetSelectedIndexQuery(), box.GetSelectedRowQuery());
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxGetSelectedIndexQuery()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(".target.getSelectedIndex", box.GetSelectedIndexQuery());
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxGetSelectedValueQuery()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(".target.getSelectedItem", box.GetSelectedValueQuery());
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxGetSelectedItemQuery()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(box.GetSelectedValueQuery(), box.GetSelectedItemQuery());
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(NotImplementedException), "The Java Swing ComboBox does not implement an equivalent selectedRows method.")]
        public void TestComboBoxGetSelectedRowsQuery()
        {
            ComboBox box = new ComboBox("box");
            box.GetSelectedRowsQuery();
        }

        [Test]
        [Category("ComboBox")]
        [ExpectedException(typeof(NotImplementedException), "The Java Swing ComboBox does not implement an equivalent selectedIndicies method.")]
        public void TestComboBoxGetSelectedIndicesQuery()
        {
            ComboBox box = new ComboBox("box");
            box.GetSelectedIndicesQuery();
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxGetSelectedValuesQuery()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(".target.getSelectedObjects", box.GetSelectedValuesQuery());
        }

        [Test]
        [Category("ComboBox")]
        public void TestComboBoxGetSelectedItemsQuery()
        {
            ComboBox box = new ComboBox("box");
            Assert.AreEqual(box.GetSelectedValuesQuery(), box.GetSelectedItemsQuery());
        }

        #endregion

        #region TextBox

        [Test]
        [Category("TextBox")]
        public void TestTextBox()
        {
            TextBox box = new TextBox("newBox");

            Assert.AreEqual("newBox", box.Name);
            Assert.AreEqual(String.Empty, box.Text);
            Assert.IsTrue(box is Component);

            TextBox anotherBox = new TextBox("anotherBox", "someText");
            Assert.AreEqual("someText", anotherBox.Text);
            Assert.AreEqual("anotherBox", anotherBox.Name);

            Assert.AreEqual("textBox", anotherBox.Type);
            Assert.AreEqual("textBox(\"anotherBox\")", anotherBox.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().textBox(\"anotherBox\")", anotherBox.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").textBox(\"anotherBox\")", anotherBox.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("TextBox")]
        public void TestTextBoxSelectedItem()
        {
            TextBox box = new TextBox("box").AddItem("item1");
            Assert.AreEqual("item1", box.SelectedItem());
        }

        [Test]
        [Category("TextBox")]
        public void TestTextBoxSelectedItems()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(box, box.AddItem("item1"));
            Assert.AreEqual("item1", box.SelectedItems()[0]);
        }

        [Test]
        [Category("TextBox")]
        public void TestTextBoxAddItem()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(box, box.AddItem("item1"));
            Assert.AreEqual(box, box.AddItem("item1").AddItem("item2"));
            Assert.AreEqual("item2", box.SelectedItem());
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use AddItem()")]
        public void TestTextBoxAddItemsException()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(box, box.AddItems(new object[] { "item1", "item2" }));
        }

        [Test]
        [Category("TextBox")]
        public void TestTextBoxAddItems()
        {
            TextBox box = new TextBox("box");
            Assert.AreEqual(box, box.AddItems(new object[] {"item1"}));
        }

        [Test]
        [Category("TextBox")]
        public void TestTextBoxRemoveItem()
        {
            TextBox box = new TextBox("box").AddItem("some text");
            Assert.AreEqual(box, box.RemoveItem("some text"));
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use RemoveItem().")]
        public void TestTextBoxRemoveItems()
        {
            TextBox box = new TextBox("box").RemoveItems(new object[] {"item1"});
        }

        [Test]
        [Category("TextBox")]
        public void TestTextBoxSelectedItemEvalMethod()
        {
            TextBox box = new TextBox("box").AddItem("selectedText");
            Assert.AreEqual("query_base.select(\"selectedText\");", box.SelectedItemEvalMethod("query_base"));
        }

        [Test]
        [Category("TextBox")]
        public void TestTextBoxSelectedItemsEvalMethod()
        {
            TextBox box = new TextBox("box").AddItem("selectedText");
            Assert.AreEqual("query_base.select(\"selectedText\");", box.SelectedItemsEvalMethod("query_base"));
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "Please supply a valid script argument.")]
        public void TestTextBoxSelectedItemEvalMethodNoArguments()
        {
            TextBox box = new TextBox("box").AddItem("selectedText");
            box.SelectedItemEvalMethod();
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "Too many script arguments. Only one is required.")]
        public void TestTextBoxSelectedItemEvalMethodTooManyArguments()
        {
            TextBox box = new TextBox("box").AddItem("selectedText");
            box.SelectedItemEvalMethod("query_base", "component");
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are strings.")]
        public void TestTextBoxValidate()
        {
            TextBox box = new TextBox("box").AddItem(2);
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTextBoxSelectedItemException()
        {
            TextBox box = new TextBox("box");
            box.SelectedItem();
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTextBoxSelectedItemsException()
        {
            TextBox box = new TextBox("box");
            box.SelectedItems();
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TextTextBoxSelectedItemEvalMethodException()
        {
            TextBox box = new TextBox("box");
            box.SelectedItemEvalMethod();
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTextBoxSelectedItemsEvalMethodException()
        {
            TextBox box = new TextBox("box");
            box.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("TextBox")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestTextBoxRemoveNonExistentItem()
        {
            TextBox box = new TextBox("box").RemoveItem("box");
        }

        #endregion

        #region Table

        [Test]
        [Category("Table")]
        public void TestTable()
        {
            Table table = new Table("newGrid");

            Assert.AreEqual("newGrid", table.Name);
            Assert.AreEqual(String.Empty, table.Text);
            Assert.IsTrue(table is Component);

            Table anotherTable = new Table("anotherTable", "someText");
            Assert.AreEqual("someText", anotherTable.Text);
            Assert.AreEqual("anotherTable", anotherTable.Name);

            Assert.AreEqual("table", anotherTable.Type);
            Assert.AreEqual("table(\"anotherTable\")", anotherTable.GetBaseComponentString() );
            Assert.AreEqual("getTestFixture().table(\"anotherTable\")", anotherTable.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").table(\"anotherTable\")", anotherTable.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("Table")]
        public void TestTableSelectedItem()
        {
            Table table = new Table("table").AddItem(new Cell(1,2));
            Assert.AreEqual("1,2", table.SelectedItem());
        }

        [Test]
        [Category("Table")]
        public void TestTableSelectedItems()
        {
            Table table = new Table("table");
            Assert.AreEqual(table, table.AddItem(new Cell(2,3)));
            Assert.AreEqual("2,3", table.SelectedItems()[0]);
        }

        [Test]
        [Category("Table")]
        public void TestTableAddItem()
        {
            Table table = new Table("table");
            Assert.AreEqual(table, table.AddItem(new Cell(3,3)));
        }

        [Test]
        [Category("Table")]
        public void TestTableAddItems()
        {
            Table table = new Table("table");
            Assert.AreEqual(2, table.AddItems(new object[] { new Cell(1,1), new Cell(2,2)}).SelectedItems().Count);
        }

        [Test]
        [Category("Table")]
        public void TestTableRemoveItem()
        {
            Cell item = new Cell(1, 1);
            Table table = new Table("table").AddItem(item);
            Assert.AreEqual(table, table.RemoveItem(item));
        }

        [Test]
        [Category("Table")]
        public void TestTableRemoveItems()
        {
            Cell item1 = new Cell(1, 1);
            Cell item2 = new Cell(2, 2);

            Table table = new Table("table").AddItems(new object[] { item1, item2 });

            Assert.AreEqual(table, table.RemoveItems(new object[] { item1, item2} ));
        }

        [Test]
        [Category("Table")]
        public void TestTableSelectedItemEvalMethod()
        {
            Table table = new Table("table").AddItem(new Cell(1,1));
            Assert.AreEqual("\r\nvar tableCell = query_base.createTableCell(1,1);\r\ncomponent.selectCell(tableCell);", 
                table.SelectedItemEvalMethod("query_base", "component"));
        }

        [Test]
        [Category("Table")]
        public void TestTableSelectedItemsEvalMethod()
        {
            Table table = new Table("table").AddItems(new object [] { new Cell(1,1), new Cell(2,2)} );
            Assert.AreEqual("\r\nvar tableCells = query_base.createTableCells([\"1,1\",\"2,2\"]);\r\ncomponent.selectCells(tableCells);", 
                table.SelectedItemsEvalMethod("query_base", "component"));
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Please supply 2 valid script arguments.")]
        public void TestTableSelectedItemMethodNoArguments()
        {
            Table table = new Table("table").AddItem(new Cell(1, 1));
            table.SelectedItemEvalMethod();
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Supply EXACTLY 2 script arguments.")]
        public void TestTableSelectedItemMethodOneArgument()
        {
            Table table = new Table("table").AddItem(new Cell(1, 1));
            table.SelectedItemEvalMethod("query_base");
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are Cells.")]
        public void TestTableValidate()
        {
            Table table = new Table("table").AddItem(2);
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTableSelectedItemException()
        {
            Table table = new Table("table");
            table.SelectedItem();
        }

        [Test]
        [Category("Table")]
 
        public void TestTableSelectedItemsEmpty()
        {
            Table table = new Table("table");
            Assert.AreEqual(0, table.SelectedItems().Count);
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTableSelectedItemEvalMethodException()
        {
            Table table = new Table("table");
            table.SelectedItemEvalMethod();
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Specify AT LEAST one selection.")]
        public void TestTableSelectedItemsEvalMethodException()
        {
            Table table = new Table("table");
            table.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Ensure there are no duplicate Items.")]
        public void TestTableAddDuplicateItem()
        {
            Table table = new Table("table").AddItem(new Cell(1, 1));
            Assert.AreEqual(table, table.AddItem(new Cell(1,1)));
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Ensure there are no duplicate Items.")]
        public void TestTableAddDuplicateItems()
        {
            Table table = new Table("table");
            Assert.AreEqual(table , table.AddItems(new object []{ new Cell(1,1), new Cell(1,1)} ));
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestTableRemoveNonExistentItem()
        {
            Table table = new Table("table").RemoveItem(new Cell(1,1));
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestTableRemoveNonExistentItems()
        {
            Table table = new Table("table").RemoveItems(new Cell(1,1), new Cell(2,2));
        }

        [Test]
        [Category("Table")]
        public void TestTableGetSelectedRowQuery()
        {
            Table table = new Table("table");
            Assert.AreEqual(".selectedRow", table.GetSelectedRowQuery());
        }

        [Test]
        [Category("Table")]
        public void TestTableGetSelectedIndexQuery()
        {
            Table table = new Table("table");
            Assert.AreEqual(table.GetSelectedRowQuery(), table.GetSelectedIndexQuery());
        }

        [Test]
        [Category("Table")]
        public void TestTableGetSelectedValueQuery()
        {
            Table table = new Table("table");
            Assert.AreEqual(".selectionValue", table.GetSelectedValueQuery());
        }

        [Test]
        [Category("Table")]
        public void TestTableGetSelectedItemQuery()
        {
            Table table = new Table("table");
            Assert.AreEqual(table.GetSelectedValueQuery(), table.GetSelectedItemQuery());
        }

        [Test]
        [Category("Table")]
        public void TestTableGetSelectedRowsQuery()
        {
            Table table = new Table("table");
            Assert.AreEqual("target.getSelectedRows", table.GetSelectedRowsQuery());
        }

        [Test]
        [Category("Table")]
        public void TestTableGetSelectedIndicesQuery()
        {
            Table table = new Table("table");
            Assert.AreEqual(table.GetSelectedRowsQuery(), table.GetSelectedIndicesQuery());
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(NotImplementedException), "The Java Swing Table does not implement an equivalent selectedValues method.")]
        public void TestTableGetSelectedValuesQuery()
        {
            Table table = new Table("table");
            table.GetSelectedValuesQuery();
        }

        [Test]
        [Category("Table")]
        [ExpectedException(typeof(NotImplementedException), "The Java Swing Table does not implement an equivalent selectedItems method.")]
        public void TestTableGetSelectedItemsQuery()
        {
            Table table = new Table("table");
            table.GetSelectedItemsQuery();
        }

        #endregion

        #region Tree

        [Test]
        [Category("Tree")]
        public void TestTree()
        {
            Tree tree = new Tree("newTree");

            Assert.AreEqual("newTree", tree.Name);
            Assert.AreEqual(String.Empty, tree.Text);
            Assert.IsTrue(tree is Component);

            Tree anotherTree = new Tree("anotherTree", "someText");
            Assert.AreEqual("someText", anotherTree.Text);
            Assert.AreEqual("anotherTree", anotherTree.Name);

            Assert.AreEqual("tree", anotherTree.Type);
            Assert.AreEqual("tree(\"anotherTree\")", anotherTree.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().tree(\"anotherTree\")", anotherTree.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").tree(\"anotherTree\")", anotherTree.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedItem()
        {
            Tree tree = new Tree("tree").AddItem("root/node/node1.1");
            Assert.AreEqual("root/node/node1.1", tree.SelectedItem());
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedItems()
        {
            Tree tree = new Tree("tree");
            Assert.AreEqual(tree, tree.AddItem("root/node/node1 1"));
            Assert.AreEqual("root/node/node1 1", tree.SelectedItems()[0]);
        }

        [Test]
        [Category("Tree")]
        public void TestTreeAddItem()
        {
            Tree tree = new Tree("tree");
            Assert.AreEqual(tree, tree.AddItem("root/middle node/leaf with <html>"));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeAddItems()
        {
            Tree tree = new Tree("tree");
            Assert.AreEqual(2, tree.AddItems(new object[] { "/middle/end", "root/middle" }).SelectedItems().Count);
        }

        [Test]
        [Category("Tree")]
        public void TestTreeRemoveItem()
        {
            Tree tree = new Tree("tree").AddItem("root/middle");
            Assert.AreEqual(tree, tree.RemoveItem("root/middle"));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeRemoveItems()
        {
            Tree tree = new Tree("tree").AddItems("root/1/2/3", "root/2/3/4", "root/3/4/5");

            Assert.AreEqual(tree, tree.RemoveItems("root/2/3/4", "root/3/4/5"));
            Assert.AreEqual(1, tree.SelectedItems().Count);
            Assert.AreEqual("root/1/2/3", tree.SelectedItems()[0]);
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedItemEvalMethod()
        {
            Tree tree = new Tree("tree").AddItem("root/node/leaf");
            Assert.AreEqual("base.selectPath(\"root/node/leaf\");", tree.SelectedItemEvalMethod("base"));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedItemsEvalMethod()
        {
            Tree tree = new Tree("tree").AddItems(new object[] { "root/1/1", "root/2/2" });
            Assert.AreEqual("base.selectPaths([\"root/1/1\",\"root/2/2\"])", tree.SelectedItemsEvalMethod("base"));
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Please supply a valid script argument.")]
        public void TestTreeSelectedItemEvalMethodNoArguments()
        {
            Tree tree = new Tree("tree").AddItem("root/1/2");
            tree.SelectedItemEvalMethod();
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Too many script arguments. Only one is required.")]
        public void TestTreeSelectedItemEvalMethodTooManyArguments()
        {
            Tree tree = new Tree("tree").AddItem("root/1/2");
            tree.SelectedItemEvalMethod("query_base", "component");
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectRow()
        {
            Tree tree = new Tree("tree").AddRow(3);
            Assert.AreEqual(3, tree.SelectedRow());
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectRows()
        {
            Tree tree = new Tree("tree").AddRows(1, 2, 3);
            Assert.AreEqual(3, tree.SelectedRows().Count);
            Assert.AreEqual(1, tree.SelectedRows()[0]);
            Assert.AreEqual(2, tree.SelectedRows()[1]);
            Assert.AreEqual(3, tree.SelectedRows()[2]);
        }

        [Test]
        [Category("Tree")]
        public void TestTreeAddRow()
        {
            Tree tree = new Tree("tree");
            Assert.AreEqual(tree, tree.AddRow(2));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeAddRows()
        {
            Tree tree = new Tree("tree");
            Assert.AreEqual(tree, tree.AddRows(2,3,4,5));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeRemoveRow()
        {
            Tree tree = new Tree("tree").AddRow(10);
            Assert.AreEqual(tree, tree.RemoveRow(10));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeRemoveRows()
        {
            Tree tree = new Tree("tree").AddRows(1, 2, 3, 4, 5, 6, 7, 8, 9);
            Assert.AreEqual(tree, tree.RemoveRows(2,4,6,8));
            Assert.AreEqual(5, tree.SelectedRows().Count);
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedRowEvalMethod()
        {
            Tree tree = new Tree("tree").AddRow(3);
            Assert.AreEqual("query_base.selectRow(3);", tree.SelectedRowEvalMethod("query_base"));
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedRowsEvalMethod()
        {
            Tree tree = new Tree("tree").AddRows(1, 2, 3, 4);
            Assert.AreEqual("query_base.selectRows([1,2,3,4]);", tree.SelectedRowsEvalMethod("query_base"));
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Please supply a valid script argument.")]
        public void TestTreeSelectedRowEvalMethodNoArguments()
        {
            Tree tree = new Tree("tree").AddRow(1);
            tree.SelectedRowEvalMethod();
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Too many script arguments. Only one is required.")]
        public void TestTreeSelectedRowEvalMethodTooManyArguments()
        {
            Tree tree = new Tree("tree").AddRow(1);
            tree.SelectedRowEvalMethod("query_base", "component");
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are strings. You can also specify rows using AddRow().")]
        public void TestTreeValidate()
        {
            Tree tree = new Tree("tree").AddItem(2);
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTreeSelectedItemException()
        {
            Tree tree = new Tree("tree");
            tree.SelectedItem();
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one row selection.")]
        public void TestTreeSelectedRowException()
        {
            Tree tree = new Tree("tree");
            tree.SelectedRow();
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedItemsEmpty()
        {
            Tree tree = new Tree("tree");
            Assert.AreEqual(0, tree.SelectedItems().Count);
        }

        [Test]
        [Category("Tree")]
        public void TestTreeSelectedRowsEmpty()
        {
            Tree tree = new Tree("tree");
            Assert.AreEqual(0, tree.SelectedRows().Count);
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTreeSelectedItemEvalMethodException()
        {
            Tree tree = new Tree("tree");
            tree.SelectedItemEvalMethod();
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one row selection.")]
        public void TestTreeSelectedRowEvalMethodException()
        {
            Tree tree = new Tree("tree");
            tree.SelectedRowEvalMethod();
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Specify AT LEAST one selection.")]
        public void TestTreeSelectedItemsEvalMethodException()
        {
            Tree tree = new Tree("tree");
            tree.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Specify AT LEAST one row.")]
        public void TestTreeSelectedRowsEvalMethodException()
        {
            Tree tree = new Tree("tree");
            tree.SelectedRowsEvalMethod();
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestTreeRemoveNonExistentItem()
        {
            Tree tree = new Tree("tree").RemoveItem("tree");
        }

        [Test]
        [Category("Tree")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestTreeRemoveNonExistentItems()
        {
            Tree tree = new Tree("tree").RemoveItems("tree1", "tree2");
        }

        #endregion

        #region Tab

        [Test]
        [Category("Tab")]
        public void TestTab()
        {
            Tab tab = new Tab("newTab");

            Assert.AreEqual("newTab", tab.Name);
            Assert.AreEqual(String.Empty, tab.Text);
            Assert.IsTrue(tab is Component);

            Tab anotherTab = new Tab("anotherTab", "someText");
            Assert.AreEqual("someText", anotherTab.Text);
            Assert.AreEqual("anotherTab", anotherTab.Name);

            Assert.AreEqual("tab", anotherTab.Type);
            Assert.AreEqual("tab(\"anotherTab\")", anotherTab.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().tab(\"anotherTab\")", anotherTab.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").tab(\"anotherTab\")", anotherTab.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region CheckBox

        [Test]
        [Category("CheckBox")]
        public void TestCheckBox()
        {
            CheckBox box = new CheckBox("newBox");

            Assert.AreEqual("newBox", box.Name);
            Assert.AreEqual(String.Empty, box.Text);
            Assert.IsTrue(box is Component);

            CheckBox anotherBox = new CheckBox("anotherBox", "someText");
            Assert.AreEqual("someText", anotherBox.Text);
            Assert.AreEqual("anotherBox", anotherBox.Name);

            Assert.AreEqual("checkBox", anotherBox.Type);
            Assert.AreEqual("checkBox(\"anotherBox\")", anotherBox.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().checkBox(\"anotherBox\")", anotherBox.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").checkBox(\"anotherBox\")", anotherBox.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region Dialog

        [Test]
        [Category("Dialog")]
        public void TestDialog()
        {
            Dialog dialog = new Dialog("newDialog");

            Assert.AreEqual("newDialog", dialog.Name);
            Assert.AreEqual(String.Empty, dialog.Text);
            Assert.IsTrue(dialog is Component);

            Dialog anotherDialog = new Dialog("anotherDialog", "someText");
            Assert.AreEqual("someText", anotherDialog.Text);
            Assert.AreEqual("anotherDialog", anotherDialog.Name);

            Assert.AreEqual("dialog", anotherDialog.Type);
            Assert.AreEqual("dialog(\"anotherDialog\")", anotherDialog.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().dialog(\"anotherDialog\")", anotherDialog.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").dialog(\"anotherDialog\")", anotherDialog.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region FileChooser

        [Test]
        [Category("FileChooser")]
        public void TestFileChooser()
        {
            FileChooser chooser = new FileChooser("newChooser");

            Assert.AreEqual("newChooser", chooser.Name);
            Assert.AreEqual(String.Empty, chooser.Text);
            Assert.IsTrue(chooser is Component);

            FileChooser anotherChooser = new FileChooser("anotherChooser", "someText");
            Assert.AreEqual("someText", anotherChooser.Text);
            Assert.AreEqual("anotherChooser", anotherChooser.Name);

            Assert.AreEqual("fileChooser", anotherChooser.Type);
            Assert.AreEqual("fileChooser(\"anotherChooser\")", anotherChooser.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().fileChooser(\"anotherChooser\")", anotherChooser.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").fileChooser(\"anotherChooser\")", anotherChooser.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserSelectedItem()
        {
            FileChooser chooser = new FileChooser("chooser").AddItem("C:\\Inetpub\\www\\test.html");
            Assert.AreEqual("C:\\Inetpub\\www\\test.html", chooser.SelectedItem());
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserSelectedItems()
        {
            FileChooser chooser = new FileChooser("chooser");
            Assert.AreEqual(chooser, chooser.AddItem("C:\\windows"));
            Assert.AreEqual("C:\\windows", chooser.SelectedItems()[0]);
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserAddItem()
        {
            FileChooser chooser = new FileChooser("chooser");
            Assert.AreEqual(chooser, chooser.AddItem("D:\\programs"));
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserAddItems()
        {
            FileChooser chooser = new FileChooser("chooser");
            Assert.AreEqual(2, chooser.AddItems(new object[] { "c:\\program files", "D:\\somewhere\\else.txt" }).SelectedItems().Count);
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserRemoveItem()
        {
            FileChooser chooser = new FileChooser("chooser").AddItem("C:\\windows");
            Assert.AreEqual(chooser, chooser.RemoveItem("C:\\windows"));
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserRemoveItems()
        {
            FileChooser chooser = new FileChooser("chooser").AddItems(new object[] { "c:\\windows", "d:\\programs" });

            Assert.AreEqual(chooser, chooser.RemoveItems(new object[] { "c:\\windows", "d:\\programs" }));
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserSelectedItemEvalMethod()
        {
            FileChooser chooser = new FileChooser("chooser").AddItem("c:\\windows");
            Assert.AreEqual("\r\nvar file = query_base.createFile(c:\\windows);\r\ncomponent.selectFile(file);", 
                chooser.SelectedItemEvalMethod("query_base", "component"));
        }

        [Test]
        [Category("FileChooser")]
        public void TestFileChooserSelectedItemsEvalMethod()
        {
            FileChooser chooser = new FileChooser("chooser").AddItems(new object[] { "c:\\windows", "d:\\programs" });
            Assert.AreEqual("\r\nvar files = query_base.createFiles([\"c:\\windows\",\"d:\\programs\"]);\r\ncomponent.selectFiles(files);", 
                chooser.SelectedItemsEvalMethod("query_base", "component"));
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "Please supply 2 valid script arguments.")]
        public void TestFileChooserSelectedItemEvalMethodNoArguments()
        {
            FileChooser chooser = new FileChooser("chooser").AddItem("c:\\windows");
            chooser.SelectedItemEvalMethod();
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "Supply EXACTLY 2 script arguments.")]
        public void TestFileChooserSelectedItemEvalMethodOneArgument()
        {
            FileChooser chooser = new FileChooser("chooser").AddItem("c:\\windows");
            chooser.SelectedItemEvalMethod("query_base");
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are strings.")]
        public void TestFileChooserValidate()
        {
            FileChooser chooser = new FileChooser("chooser").AddItem(2);
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestFileChooserSelectedItemException()
        {
            FileChooser chooser = new FileChooser("chooser");
            chooser.SelectedItem();
        }

        [Test]
        [Category("FileChooser")]

        public void TestFileChooserSelectedItemsEmpty()
        {
            FileChooser chooser = new FileChooser("chooser");
            Assert.AreEqual(0, chooser.SelectedItems().Count);
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestFileChooserSelectedItemEvalMethodException()
        {
            FileChooser chooser = new FileChooser("chooser");
            chooser.SelectedItemEvalMethod();
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "Specify AT LEAST one selection.")]
        public void TestFileChooserSelectedItemsEvalMethodException()
        {
            FileChooser chooser = new FileChooser("chooser");
            chooser.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "This component requires a valid file/directory path.")]
        public void TestFileChooserInvalidPathAddItem()
        {
            FileChooser chooser = new FileChooser("chooser").AddItem("*/%$");
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "This component requires a valid file/directory path.")]
        public void TestFileChooserInvalidPathAddItems()
        {
            FileChooser chooser = new FileChooser("chooser");
            Assert.AreEqual(chooser, chooser.AddItems(new object[] { "c:\\windows", "*/%$" }));
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestFileChooserRemoveNonExistentItem()
        {
            FileChooser chooser = new FileChooser("chooser").RemoveItem("c:\\windows");
        }

        [Test]
        [Category("FileChooser")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestFileChooserRemoveNonExistentItems()
        {
            FileChooser chooser = new FileChooser("chooser").RemoveItems("c:\\windows", "d:\\programs");
        }

        #endregion

        #region Label

        [Test]
        [Category("Label")]
        public void TestLabel()
        {
            Label label = new Label("newLabel");

            Assert.AreEqual("newLabel", label.Name);
            Assert.AreEqual(String.Empty, label.Text);
            Assert.IsTrue(label is Component);

            Label anotherLabel = new Label("anotherLabel", "someText");
            Assert.AreEqual("someText", anotherLabel.Text);
            Assert.AreEqual("anotherLabel", anotherLabel.Name);

            Assert.AreEqual("label", anotherLabel.Type);
            Assert.AreEqual("label(\"anotherLabel\")", anotherLabel.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().label(\"anotherLabel\")", anotherLabel.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").label(\"anotherLabel\")", anotherLabel.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region List

        [Test]
        [Category("List")]
        public void TestList()
        {
            List list = new List("newList");

            Assert.AreEqual("newList", list.Name);
            Assert.AreEqual(String.Empty, list.Text);
            Assert.IsTrue(list is Component);

            List anotherList = new List("anotherList", "someText");
            Assert.AreEqual("someText", anotherList.Text);
            Assert.AreEqual("anotherList", anotherList.Name);

            Assert.AreEqual("list", anotherList.Type);
            Assert.AreEqual("list(\"anotherList\")", anotherList.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().list(\"anotherList\")", anotherList.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").list(\"anotherList\")", anotherList.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("List")]
        public void TestListSelectedItem()
        {
            List list = new List("list").AddItem("item");
            Assert.AreEqual("item", list.SelectedItem());
        }

        [Test]
        [Category("List")]
        public void TestListSelectedItems()
        {
            List list = new List("list");
            Assert.AreEqual(list, list.AddItems("items"));
            Assert.AreEqual("items", list.SelectedItems()[0]);
        }

        [Test]
        [Category("List")]
        public void TestListAddItem()
        {
            List list = new List("list");
            Assert.AreEqual(list, list.AddItem("item"));
        }

        [Test]
        [Category("List")]
        public void TestListAddItems()
        {
            List list = new List("list");
            Assert.AreEqual(2, list.AddItems("item1", "item2").SelectedItems().Count);
        }

        [Test]
        [Category("List")]
        public void TestListRemoveItem()
        {
            List list = new List("list").AddItem("item");
            Assert.AreEqual(list, list.RemoveItem("item"));
        }

        [Test]
        [Category("List")]
        public void TestListRemoveItems()
        {
            List list = new List("list").AddItems("item1", "item2");

            Assert.AreEqual(list, list.RemoveItems("item1", "item2"));
            Assert.AreEqual(0, list.SelectedItems().Count);
        }

        [Test]
        [Category("List")]
        public void TestListSelectedItemEvalMethod()
        {
            List list = new List("list").AddItem("item");
            Assert.AreEqual("component.selectItem(\"item\");", list.SelectedItemEvalMethod("query_base", "component"));
        }

        [Test]
        [Category("List")]
        public void TestListSelectedItemsEvalMethod()
        {
            List list = new List("list").AddItems("item1", "item2");
            Assert.AreEqual("\r\nvar stringArray = query_base.createStringArray([\"item1\",\"item2\"]);\r\ncomponent.selectItems(stringArray);", 
                list.SelectedItemsEvalMethod("query_base", "component"));
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "Please supply a valid script argument.")]
        public void TestListSelectedItemEvalMethodNoArguments()
        {
            List list = new List("list").AddItem("item");
            list.SelectedItemEvalMethod();
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "Supply EXACTLY 2 script arguments.")]
        public void TestListSelectedItemMethodOneArgument()
        {
            List list = new List("list").AddItem("item");
            list.SelectedItemEvalMethod("query_base");
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are strings.")]
        public void TestListValidate()
        {
            List list = new List("list").AddItem(2);
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestListSelectedItemException()
        {
            List list = new List("list");
            list.SelectedItem();
        }

        [Test]
        [Category("List")]

        public void TestListSelectedItemsEmpty()
        {
            List list = new List("list");
            Assert.AreEqual(0, list.SelectedItems().Count);
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestListSelectedItemEvalMethodException()
        {
            List list = new List("list");
            list.SelectedItemEvalMethod();
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "Specify AT LEAST one selection.")]
        public void TestListSelectedItemsEvalMethodException()
        {
            List list = new List("list");
            list.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestListRemoveNonExistentItem()
        {
            List list = new List("list").RemoveItem("list");
        }

        [Test]
        [Category("List")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestListRemoveNonExistentItems()
        {
            List list = new List("list").RemoveItems("list1", "list2");
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedRowQuery()
        {
            List list = new List("list");
            Assert.AreEqual(list.GetSelectedIndexQuery(), list.GetSelectedRowQuery());
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedIndexQuery()
        {
            List list = new List("list");
            Assert.AreEqual(".target.getSelectedIndex", list.GetSelectedIndexQuery());
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedValueQuery()
        {
            List list = new List("list");
            Assert.AreEqual(".target.getSelectedValue", list.GetSelectedValueQuery());
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedItemQuery()
        {
            List list = new List("list");
            Assert.AreEqual(list.GetSelectedValueQuery(), list.GetSelectedItemQuery());
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedRowsQuery()
        {
            List list = new List("list");
            Assert.AreEqual(list.GetSelectedIndicesQuery(), list.GetSelectedRowsQuery());
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedIndicesQuery()
        {
            List list = new List("list");
            Assert.AreEqual(".target.getSelectedIndices", list.GetSelectedIndicesQuery());
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedValuesQuery()
        {
            List list = new List("list");
            Assert.AreEqual(".target.getSelectedValues", list.GetSelectedValuesQuery());
        }

        [Test]
        [Category("List")]
        public void TestListGetSelectedItemsQuery()
        {
            List list = new List("list");
            Assert.AreEqual(list.GetSelectedValuesQuery(), list.GetSelectedItemsQuery());
        }

        #endregion

        #region Panel

        [Test]
        [Category("Panel")]
        public void TestPanel()
        {
            Panel panel = new Panel("newPanel");

            Assert.AreEqual("newPanel", panel.Name);
            Assert.AreEqual(String.Empty, panel.Text);
            Assert.IsTrue(panel is Component);

            Panel anotherPanel = new Panel("anotherPanel", "someText");
            Assert.AreEqual("someText", anotherPanel.Text);
            Assert.AreEqual("anotherPanel", anotherPanel.Name);

            Assert.AreEqual("panel", anotherPanel.Type);
            Assert.AreEqual("panel(\"anotherPanel\")", anotherPanel.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().panel(\"anotherPanel\")", anotherPanel.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").panel(\"anotherPanel\")", anotherPanel.GetQueryString("prefix(\"prefix\")"));
        }


        #endregion

        #region RadioButton

        [Test]
        [Category("RadioButton")]
        public void TestRadioButton()
        {
            RadioButton button = new RadioButton("newButton");

            Assert.AreEqual("newButton", button.Name);
            Assert.AreEqual(String.Empty, button.Text);
            Assert.IsTrue(button is Component);

            RadioButton anotherButton = new RadioButton("anotherButton", "someText");
            Assert.AreEqual("someText", anotherButton.Text);
            Assert.AreEqual("anotherButton", anotherButton.Name);

            Assert.AreEqual("radioButton", anotherButton.Type);
            Assert.AreEqual("radioButton(\"anotherButton\")", anotherButton.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().radioButton(\"anotherButton\")", anotherButton.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").radioButton(\"anotherButton\")", anotherButton.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region ScrollBar

        [Test]
        [Category("ScrollBar")]
        public void TestScrollBar()
        {
            ScrollBar bar = new ScrollBar("newBar");

            Assert.AreEqual("newBar", bar.Name);
            Assert.AreEqual(String.Empty, bar.Text);
            Assert.IsTrue(bar is Component);

            ScrollBar anotherBar = new ScrollBar("anotherBar", "someText");
            Assert.AreEqual("someText", anotherBar.Text);
            Assert.AreEqual("anotherBar", anotherBar.Name);

            Assert.AreEqual("scrollBar", anotherBar.Type);
            Assert.AreEqual("scrollBar(\"anotherBar\")", anotherBar.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().scrollBar(\"anotherBar\")", anotherBar.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").scrollBar(\"anotherBar\")", anotherBar.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region ScrollPane

        [Test]
        [Category("ScrollPane")]
        public void TestScrollPane()
        {
            ScrollPane pane = new ScrollPane("newPane");

            Assert.AreEqual("newPane", pane.Name);
            Assert.AreEqual(String.Empty, pane.Text);
            Assert.IsTrue(pane is Component);

            ScrollPane anotherPane = new ScrollPane("anotherPane", "someText");
            Assert.AreEqual("someText", anotherPane.Text);
            Assert.AreEqual("anotherPane", anotherPane.Name);

            Assert.AreEqual("scrollPane", anotherPane.Type);
            Assert.AreEqual("scrollPane(\"anotherPane\")", anotherPane.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().scrollPane(\"anotherPane\")", anotherPane.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").scrollPane(\"anotherPane\")", anotherPane.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region Slider

        [Test]
        [Category("Slider")]
        public void TestSlider()
        {
            Slider slider = new Slider("newSlider");

            Assert.AreEqual("newSlider", slider.Name);
            Assert.AreEqual(String.Empty, slider.Text);
            Assert.IsTrue(slider is Component);

            Slider anotherSlider = new Slider("anotherSlider", "someText");
            Assert.AreEqual("someText", anotherSlider.Text);
            Assert.AreEqual("anotherSlider", anotherSlider.Name);

            Assert.AreEqual("slider", anotherSlider.Type);
            Assert.AreEqual("slider(\"anotherSlider\")", anotherSlider.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().slider(\"anotherSlider\")", anotherSlider.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").slider(\"anotherSlider\")", anotherSlider.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region Spinner

        [Test]
        [Category("Spinner")]
        public void TestSpinner()
        {
            Spinner spinner = new Spinner("newSpinner");

            Assert.AreEqual("newSpinner", spinner.Name);
            Assert.AreEqual(String.Empty, spinner.Text);
            Assert.IsTrue(spinner is Component);

            Spinner anotherSpinner = new Spinner("anotherSpinner", "someText");
            Assert.AreEqual("someText", anotherSpinner.Text);
            Assert.AreEqual("anotherSpinner", anotherSpinner.Name);

            Assert.AreEqual("spinner", anotherSpinner.Type);
            Assert.AreEqual("spinner(\"anotherSpinner\")", anotherSpinner.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().spinner(\"anotherSpinner\")", anotherSpinner.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").spinner(\"anotherSpinner\")", anotherSpinner.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("Spinner")]
        public void TestSpinnerSelectedItem()
        {
            Spinner spin = new Spinner("spin").AddItem("item1");
            Assert.AreEqual("item1", spin.SelectedItem());
        }

        [Test]
        [Category("Spinner")]
        public void TestSpinnerSelectedItems()
        {
            Spinner spin = new Spinner("spin");
            Assert.AreEqual(spin, spin.AddItem("item1"));
            Assert.AreEqual("item1", spin.SelectedItems()[0]);
        }

        [Test]
        [Category("Spinner")]
        public void TestSpinnerAddItem()
        {
            Spinner spin = new Spinner("spin");
            Assert.AreEqual(spin, spin.AddItem("item1"));
            Assert.AreEqual(spin, spin.AddItem("item1").AddItem("item2"));
            Assert.AreEqual("item2", spin.SelectedItem());
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use AddItem()")]
        public void TestSpinnerddItemsException()
        {
            Spinner spin = new Spinner("spin");
            Assert.AreEqual(spin, spin.AddItems(new object[] { "item1", "item2" }));
        }

        [Test]
        [Category("Spinner")]
        public void TestSpinnerAddItems()
        {
            Spinner spin = new Spinner("spin");
            Assert.AreEqual(spin, spin.AddItems(new object[] { "item1" }));
        }

        [Test]
        [Category("Spinner")]
        public void TestSpinnerRemoveItem()
        {
            Spinner spin = new Spinner("spin").AddItem("some text");
            Assert.AreEqual(spin, spin.RemoveItem("some text"));
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use RemoveItem().")]
        public void TestSpinnerRemoveItems()
        {
            Spinner spin = new Spinner("spin").RemoveItems(new object[] { "item1" });
        }

        [Test]
        [Category("Spinner")]
        public void TestSpinnerSelectedItemEvalMethod()
        {
            Spinner spin = new Spinner("spin").AddItem("selectedText");
            Assert.AreEqual("query_base.select(\"selectedText\");", spin.SelectedItemEvalMethod("query_base"));
        }

        [Test]
        [Category("Spinner")]
        public void TestSpinnerSelectedItemsEvalMethod()
        {
            Spinner spin = new Spinner("spin").AddItem("selectedText");
            Assert.AreEqual("query_base.select(\"selectedText\");", spin.SelectedItemsEvalMethod("query_base"));
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "Please supply a valid script argument.")]
        public void TestSpinnerSelectedItemEvalMethodNoArguments()
        {
            Spinner spin = new Spinner("spin").AddItem("selectedText");
            spin.SelectedItemEvalMethod();
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "Too many script arguments. Only one is required.")]
        public void TestSpinnerSelectedItemEvalMethodTooManyArguments()
        {
            Spinner spin = new Spinner("spin").AddItem("selectedText");
            spin.SelectedItemEvalMethod("query_base", "component");
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are strings.")]
        public void TestSpinneralidate()
        {
            Spinner spin = new Spinner("spin").AddItem(2);
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestSpinnerSelectedItemException()
        {
            Spinner spin = new Spinner("spin");
            spin.SelectedItem();
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestSpinnerSelectedItemsException()
        {
            Spinner spin = new Spinner("spin");
            spin.SelectedItems();
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TextSpinnerSelectedItemEvalMethodException()
        {
            Spinner spin = new Spinner("spin");
            spin.SelectedItemEvalMethod();
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestSpinnerSelectedItemsEvalMethodException()
        {
            Spinner spin = new Spinner("spin");
            spin.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("Spinner")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestSpinnerRemoveNonExistentItem()
        {
            Spinner spin = new Spinner("spin").RemoveItem("spin");
        }

        #endregion

        #region SplitPane

        [Test]
        [Category("SplitPane")]
        public void TestSplitPane()
        {
            SplitPane pane = new SplitPane("newPane");

            Assert.AreEqual("newPane", pane.Name);
            Assert.AreEqual(String.Empty, pane.Text);
            Assert.IsTrue(pane is Component);

            SplitPane anotherPane = new SplitPane("anotherPane", "someText");
            Assert.AreEqual("someText", anotherPane.Text);
            Assert.AreEqual("anotherPane", anotherPane.Name);

            Assert.AreEqual("splitPane", anotherPane.Type);
            Assert.AreEqual("splitPane(\"anotherPane\")", anotherPane.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().splitPane(\"anotherPane\")", anotherPane.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").splitPane(\"anotherPane\")", anotherPane.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region TabbedPane

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPane()
        {
            TabbedPane pane = new TabbedPane("newPane");

            Assert.AreEqual("newPane", pane.Name);
            Assert.AreEqual(String.Empty, pane.Text);
            Assert.IsTrue(pane is Component);

            TabbedPane anotherPane = new TabbedPane("anotherPane", "someText");
            Assert.AreEqual("someText", anotherPane.Text);
            Assert.AreEqual("anotherPane", anotherPane.Name);

            Assert.AreEqual("tabbedPane", anotherPane.Type);
            Assert.AreEqual("tabbedPane(\"anotherPane\")", anotherPane.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().tabbedPane(\"anotherPane\")", anotherPane.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").tabbedPane(\"anotherPane\")", anotherPane.GetQueryString("prefix(\"prefix\")"));
        }

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPaneSelectedItem()
        {
            TabbedPane pane = new TabbedPane("pane").AddItem("item1");
            Assert.AreEqual("item1", pane.SelectedItem());
        }

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPaneSelectedItems()
        {
            TabbedPane pane = new TabbedPane("pane");
            Assert.AreEqual(pane, pane.AddItem("item1"));
            Assert.AreEqual("item1", pane.SelectedItems()[0]);
        }

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPaneAddItem()
        {
            TabbedPane pane = new TabbedPane("pane");
            Assert.AreEqual(pane, pane.AddItem("item1"));
            Assert.AreEqual(pane, pane.AddItem("item1").AddItem("item2"));
            Assert.AreEqual("item2", pane.SelectedItem());
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use AddItem()")]
        public void TestTabbedPaneAddItemsException()
        {
            TabbedPane pane = new TabbedPane("pane");
            Assert.AreEqual(pane, pane.AddItems(new object[] { "item1", "item2" }));
        }

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPaneAddItems()
        {
            TabbedPane pane = new TabbedPane("pane");
            Assert.AreEqual(pane, pane.AddItems(new object[] { "item1" }));
        }

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPaneRemoveItem()
        {
            TabbedPane pane = new TabbedPane("pane").AddItem("some text");
            Assert.AreEqual(pane, pane.RemoveItem("some text"));
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "This component can only have a single item at a time. Use RemoveItem().")]
        public void TestTabbedPaneRemoveItems()
        {
            TabbedPane pane = new TabbedPane("pane").RemoveItems(new object[] { "item1" });
        }

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPaneSelectedItemEvalMethod()
        {
            TabbedPane pane = new TabbedPane("pane").AddItem("selectedText");
            Assert.AreEqual("query_base.selectTab(\"selectedText\");", pane.SelectedItemEvalMethod("query_base"));
        }

        [Test]
        [Category("TabbedPane")]
        public void TestTabbedPaneSelectedItemsEvalMethod()
        {
            TabbedPane pane = new TabbedPane("pane").AddItem("selectedText");
            Assert.AreEqual("query_base.selectTab(\"selectedText\");", pane.SelectedItemsEvalMethod("query_base"));
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "Please supply a valid script argument.")]
        public void TestTabbedPaneSelectedItemEvalMethodNoArguments()
        {
            TabbedPane pane = new TabbedPane("pane").AddItem("selectedText");
            pane.SelectedItemEvalMethod();
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "Too many script arguments. Only one is required.")]
        public void TestTabbedPaneSelectedItemEvalMethodTooManyArguments()
        {
            TabbedPane pane = new TabbedPane("pane").AddItem("selectedText");
            pane.SelectedItemEvalMethod("query_base", "component");
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "This component can only use Items that are strings.")]
        public void TestTabbedPaneValidate()
        {
            TabbedPane pane = new TabbedPane("pane").AddItem(2);
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTabbedPaneSelectedItemException()
        {
            TabbedPane pane = new TabbedPane("pane");
            pane.SelectedItem();
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTabbedPaneSelectedItemsException()
        {
            TabbedPane pane = new TabbedPane("pane");
            pane.SelectedItems();
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TextTabbedPaneSelectedItemEvalMethodException()
        {
            TabbedPane pane = new TabbedPane("pane");
            pane.SelectedItemEvalMethod();
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "Specify EXACTLY one selection.")]
        public void TestTabbedPaneSelectedItemsEvalMethodException()
        {
            TabbedPane pane = new TabbedPane("pane");
            pane.SelectedItemsEvalMethod();
        }

        [Test]
        [Category("TabbedPane")]
        [ExpectedException(typeof(ArgumentException), "Attempt to remove a non-existent item.")]
        public void TestTabbedPaneRemoveNonExistentItem()
        {
            TabbedPane pane = new TabbedPane("pane").RemoveItem("pane");
        }

        #endregion

        #region ToggleButton

        [Test]
        [Category("ToggleButton")]
        public void TestToggleButton()
        {
            ToggleButton button = new ToggleButton("newButton");

            Assert.AreEqual("newButton", button.Name);
            Assert.AreEqual(String.Empty, button.Text);
            Assert.IsTrue(button is Component);

            ToggleButton anotherButton = new ToggleButton("anotherButton", "someText");
            Assert.AreEqual("someText", anotherButton.Text);
            Assert.AreEqual("anotherButton", anotherButton.Name);

            Assert.AreEqual("toggleButton", anotherButton.Type);
            Assert.AreEqual("toggleButton(\"anotherButton\")", anotherButton.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().toggleButton(\"anotherButton\")", anotherButton.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").toggleButton(\"anotherButton\")", anotherButton.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region ToolBar

        [Test]
        [Category("ToolBar")]
        public void TestToolBar()
        {
            ToolBar bar = new ToolBar("newBar");

            Assert.AreEqual("newBar", bar.Name);
            Assert.AreEqual(String.Empty, bar.Text);
            Assert.IsTrue(bar is Component);

            ToolBar anotherBar = new ToolBar("anotherBar", "someText");
            Assert.AreEqual("someText", anotherBar.Text);
            Assert.AreEqual("anotherBar", anotherBar.Name);

            Assert.AreEqual("toolBar", anotherBar.Type);
            Assert.AreEqual("toolBar(\"anotherBar\")", anotherBar.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().toolBar(\"anotherBar\")", anotherBar.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").toolBar(\"anotherBar\")", anotherBar.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        #region MenuItem

        [Test]
        [Category("MenuItem")]
        public void TestMenuItem()
        {
            MenuItem item = new MenuItem("newItem");

            Assert.AreEqual("newItem", item.Name);
            Assert.AreEqual(String.Empty, item.Text);
            Assert.IsTrue(item is Component);

            MenuItem anotherItem = new MenuItem("anotherItem", "someText");
            Assert.AreEqual("someText", anotherItem.Text);
            Assert.AreEqual("anotherItem", anotherItem.Name);

            Assert.AreEqual("menuItem", anotherItem.Type);
            Assert.AreEqual("menuItem(\"anotherItem\")", anotherItem.GetBaseComponentString());
            Assert.AreEqual("getTestFixture().menuItem(\"anotherItem\")", anotherItem.GetQueryString());
            Assert.AreEqual("getTestFixture().prefix(\"prefix\").menuItem(\"anotherItem\")", anotherItem.GetQueryString("prefix(\"prefix\")"));
        }

        #endregion

        [Test]
        public void TestIHasCoordinates()
        {
            Table table = new Table("table", "text");
            
            Assert.IsNotNull(table.Coordinates());
            Assert.IsTrue(table.Coordinates().Equals(new Coordinates(0, 0)));
            Assert.AreEqual(table, table.Coordinates(new Coordinates(10, 10)));
            Assert.IsTrue(table.Coordinates().Equals(new Coordinates(10, 10)));
        }

        [Test]
        public void TestIHasTimeOut()
        {
            Tree tree = new Tree("tree", "text");

            Assert.AreEqual(String.Empty, tree.TimeOut());
            Assert.AreEqual(tree, tree.TimeOut("timeOut"));
            Assert.AreEqual("timeOut", tree.TimeOut());
        }

        [Test]
        public void TestIHasValue()
        {
            TextBox box = new TextBox("box", "text");

            Assert.AreEqual( String.Empty, box.Value() );
            Assert.AreEqual( box, box.Value("value") );
            Assert.AreEqual( "value", box.Value() );
        }
    }
}
