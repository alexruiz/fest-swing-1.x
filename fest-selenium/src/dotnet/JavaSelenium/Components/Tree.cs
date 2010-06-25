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
using System.Text.RegularExpressions;
using JavaSelenium.Components.Interfaces;

namespace JavaSelenium.Components
{
    public class Tree : Component, IHasTimeOut<Tree>, IHasRowSelection<Tree>, IHasCoordinates<Tree>
    {
        private const string TYPE_STRING = "tree";
        private string _timeOut;
        private readonly List<string> _selections = new List<string>();
        private List<int> _rows = new List<int>();
        private Coordinates _coordinates;

        public Tree(string pName) : base(pName, TYPE_STRING)
        {}

        public Tree(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasTimeOut<Tree>

        public string TimeOut()
        {
            if (_timeOut == null)
                _timeOut = String.Empty;
            return _timeOut;
        }

        public Tree TimeOut(string pTimeOut)
        {
            _timeOut = pTimeOut;
            return this;
        }

        #endregion

        #region Implementation of IHasSelection<Tree>

        public string SelectedItem()
        {
            _ValidateSingleSelection();
            return _selections[0];
        }

        public List<string> SelectedItems()
        {
            return _selections;
        }

        public Tree AddItem(object pItem)
        {
            _selections.Add(_ValidateTreePath(pItem));
            return this;
        }

        public Tree AddItems(params object[] pItems)
        {
            foreach (object item in pItems)
                _selections.Add(_ValidateTreePath(item));

            return this;
        }

        public Tree RemoveItem(object pItem)
        {
            if (!_selections.Remove(_ValidateTreePath(pItem)))
                throw new ArgumentException("Attempt to remove a non-existent item.");
            return this;
        }

        public Tree RemoveItems(params object[] pItems)
        {
            foreach (object item in pItems)
            {
                if (!_selections.Remove(_ValidateTreePath(item)))
                    throw new ArgumentException("Attempt to remove a non-existent item.");
            }
            return this;  
        }

        public string SelectedItemEvalMethod(params string[] pScriptArguments)
        {
            _ValidateSingleSelection();
            _ValidateScriptArguments(pScriptArguments);

            // requires the Selenium.JSPrefix and the CurrentComponent.GetQueryString
            return String.Format("{0}.selectPath(\"" + _selections[0] + "\");", pScriptArguments[0]);
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

            // requires the Selenium.JSPrefix and the CurrentComponent.GetQueryString
            return String.Format("{0}.selectPaths(" + items.ToString().Substring(0, items.Length - 1) + "])", pScriptArguments[0]);
        }

        #endregion

        #region Implementation of IHasRowSelection<Tree>

        public int SelectedRow()
        {
            _ValidateSingleRowSelection();
            return _rows[0];
        }

        public List<int> SelectedRows()
        {
            return _rows;
        }

        public Tree AddRow(int pRow)
        {
            _rows.Add(pRow);
            return this;
        }

        public Tree AddRows(params int[] pRows)
        {
            foreach (int row in pRows)
                _rows.Add(row);
            return this;
        }

        public Tree RemoveRow(int pRow)
        {
            if (!_rows.Remove(pRow))
                throw new ArgumentException("Attempt to remove a non-existent item.");
            return this;
        }

        public Tree RemoveRows(params int[] pRows)
        {
            foreach (int row in pRows)
            {
                if (!_rows.Remove(row))
                    throw new ArgumentException("Attempt to remove a non-existent item.");
            }
            return this; 
        }

        public string SelectedRowEvalMethod(params string[] pScriptArguments)
        {
            _ValidateSingleRowSelection();
            _ValidateScriptArguments(pScriptArguments);

            // requires the Selenium.JSPrefix and the CurrentComponent.GetQueryString
            return String.Format("{0}.selectRow(" + _rows[0] + ");", pScriptArguments[0]);
        }

        public string SelectedRowsEvalMethod(params string[] pScriptArguments)
        {
            if (_rows.Count == 0)
                throw new ArgumentException("Specify AT LEAST one row.");

            _ValidateScriptArguments(pScriptArguments);

            StringBuilder rows = new StringBuilder();

            // Note: Array delimiters required
            rows.Append("[");

            foreach (int row in SelectedRows())
                rows.Append(String.Format("{0},", row));

            // requires the Selenium.JSPrefix and the CurrentComponent.GetQueryString
            return String.Format("{0}.selectRows(" + rows.ToString().Substring(0, rows.Length - 1) + "]);", pScriptArguments[0]); 
        }

        #endregion

        #region Validations

        private string _Validate(object pItem)
        {
            if (!(pItem is string))
                throw new ArgumentException("This component can only use Items that are strings. You can also specify rows using AddRow().");

            return pItem as string;
        }

        private void _ValidateSingleSelection()
        {
            if (_selections.Count > 1 || _selections.Count == 0)
                throw new ArgumentException("Specify EXACTLY one selection.");
        }

        private void _ValidateSingleRowSelection()
        {
            if (_rows.Count > 1 || _rows.Count == 0)
                throw new ArgumentException("Specify EXACTLY one row selection.");
        }

        private string _ValidateTreePath(object pItem)
        {
            string path = _Validate(pItem);

            const string pattern = @"^(.*?/|.*?\\)?([^\./|^\.\\]+)(?:\.([^\\]*)|)$";
            Regex reg = new Regex(pattern, RegexOptions.Compiled | RegexOptions.IgnoreCase);

            if (!reg.IsMatch(path))
                throw new ArgumentException("This component requires a valid tree path.");

            return path;
        }

        private void _ValidateScriptArguments(string[] pScriptArguments)
        {
            if (pScriptArguments.Length == 0)
                throw new ArgumentException("Please supply a valid script argument.");

            if (pScriptArguments.Length > 1)
                throw new ArgumentException("Too many script arguments. Only one is required.");
        }

        #endregion

        #region Implementation of IHasCoordinates<Tree>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public Tree Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion
    }
}
