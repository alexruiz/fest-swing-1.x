// Date: Oct 15 2009
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
using System.Collections.Generic;
using System.Text;
using JavaSelenium.Components.Interfaces;

namespace JavaSelenium.Components
{
    public class Table : Component, IHasCoordinates<Table>, IHasSelection<Table>, IRequiresJavaObjectCreation, IHasMultipleSelectionValues
    {
        private const string TYPE_STRING = "table";
        private Coordinates _coordinates;
        private readonly List<Cell> _selections = new List<Cell>();

        public Table(string pName) : base(pName, TYPE_STRING)
        {}

        public Table(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasCoordinates<Table>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public Table Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion

        #region Implementation of IHasSelection<Table>

        public string SelectedItem()
        {
            _ValidateSingleSelection();
            return _selections[0].ToString();
        }

        public List<string> SelectedItems()
        {
            List<string> items = new List<string>();
            foreach( Cell cell in _selections)
                items.Add(cell.ToString());
            return items;
        }

        public Table AddItem(object pItem)
        {
            _selections.Add(_ValidateSelectionIsUnique(pItem));
            return this;
        }

        public Table AddItems(params object[] pItems)
        {
            foreach (object item in pItems)
                _selections.Add(_ValidateSelectionIsUnique(item));
            return this;
        }

        public Table RemoveItem(object pItem)
        {
            if (!_selections.Remove(_Validate(pItem)))
                throw new ArgumentException("Attempt to remove a non-existent item.");
            return this;
        }

        public Table RemoveItems(params object[] pItems)
        {
            foreach (object item in pItems)
            {
                if ( ! _selections.Remove(_Validate(item)) )
                    throw new ArgumentException("Attempt to remove a non-existent item.");
            }
            return this;                
        }

        public string SelectedItemEvalMethod(params string[] pScriptArguments)
        {
            _ValidateSingleSelection();
            _ValidateScriptArguments(pScriptArguments);

            string script = String.Format(@"
var tableCell = {0}.createTableCell({1});
{2}.selectCell(tableCell);"
                , pScriptArguments[0] //the Selenium.JSPrefix and _GetQueryBase
                , SelectedItem()
                , pScriptArguments[1] //the Selenium.JSPrefix and the CurrentComponent.GetQueryString()
            );

            return script;
        }

        public string SelectedItemsEvalMethod(params string[] pScriptArguments)
        {
            if (_selections.Count == 0)
                throw new ArgumentException("Specify AT LEAST one selection.");

            _ValidateScriptArguments(pScriptArguments);

            StringBuilder items = new StringBuilder();

            // Note: Array delimiters required
            items.Append("[");

            foreach (string item in SelectedItems())
                items.Append(String.Format("\"{0}\",", item));

            string script = String.Format(@"
var tableCells = {0}.createTableCells({1});
{2}.selectCells(tableCells);"
               , pScriptArguments[0]                                    //the Selenium.JSPrefix and _GetQueryBase
               , items.ToString().Substring(0, items.Length - 1) + "]"  //trucate trailing comma, the array of cell dimensions
               , pScriptArguments[1]                                    //the Selenium.JSPrefix and the CurrentComponent.GetQueryString()
           );

            return script;
        }

        #endregion

        #region Validations

        private Cell _Validate(object pItem)
        {
            if (!(pItem is Cell))
                throw new ArgumentException("This component can only use Items that are Cells.");

            return pItem as Cell;
        }

        private void _ValidateSingleSelection()
        {
            if (_selections.Count > 1 || _selections.Count == 0)
                throw new ArgumentException("Specify EXACTLY one selection.");
        }

        private Cell _ValidateSelectionIsUnique(object pItem)
        {
            Cell validCell = _Validate(pItem);
            foreach(Cell cell in _selections)
            {
                if (cell.Equals(validCell))
                    throw new ArgumentException("Ensure there are no duplicate Items.");    
            }
            return validCell;
        }

        private void _ValidateScriptArguments(string[] pScriptArguments)
        {
            if (pScriptArguments.Length == 0)
                throw new ArgumentException("Please supply 2 valid script arguments.");

            if (pScriptArguments.Length != 2)
                throw new ArgumentException("Supply EXACTLY 2 script arguments.");
        }

        #endregion

        #region Implementation of IHasSelectionValue

        public string GetSelectedRowQuery()
        {
            return ".selectedRow";
        }

        public string GetSelectedIndexQuery()
        {
            return GetSelectedRowQuery();
        }

        public string GetSelectedValueQuery()
        {
            return ".selectionValue";
        }

        public string GetSelectedItemQuery()
        {
            return GetSelectedValueQuery();
        }

        #endregion

        #region Implementation of IHasMultipleSelectionValues

        public string GetSelectedRowsQuery()
        {
            return "target.getSelectedRows";
        }

        public string GetSelectedIndicesQuery()
        {
            return GetSelectedRowsQuery();
        }

        public string GetSelectedValuesQuery()
        {
            throw new NotImplementedException("The Java Swing Table does not implement an equivalent selectedValues method.");
        }

        public string GetSelectedItemsQuery()
        {
            throw new NotImplementedException("The Java Swing Table does not implement an equivalent selectedItems method.");
        }

        #endregion
    }
}
