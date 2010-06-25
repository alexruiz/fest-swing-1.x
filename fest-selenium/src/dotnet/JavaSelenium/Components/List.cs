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
    public class List : Component, IHasSelection<List>, IHasCoordinates<List>, IHasMultipleSelectionValues, IRequiresJavaObjectCreation
    {
        private const string TYPE_STRING = "list";
        private readonly List<string> _selections = new List<string>();
        private Coordinates _coordinates;

        public List(string pName) : base(pName, TYPE_STRING)
        {}

        public List(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasSelection<List>

        public string SelectedItem()
        {
            _ValidateSingleSelection();
            return _selections[0];
        }

        public List<string> SelectedItems()
        {
            return _selections;
        }

        public List AddItem(object pItem)
        {
            _selections.Add(_Validate(pItem));
            return this;
        }

        public List AddItems(params object[] pItems)
        {
            foreach (object item in pItems)
                _selections.Add(_Validate(item));
            
            return this;
        }

        public List RemoveItem(object pItem)
        {
            if (!_selections.Remove(_Validate(pItem)))
                throw new ArgumentException("Attempt to remove a non-existent item.");
            return this;
        }

        public List RemoveItems(params object[] pItems)
        {
            foreach (object item in pItems)
            {
                if (!_selections.Remove(_Validate(item)))
                    throw new ArgumentException("Attempt to remove a non-existent item.");
            }
            return this;
        }

        public string SelectedItemEvalMethod(params string[] pScriptArguments)
        {
            _ValidateSingleSelection();
            _ValidateScriptArguments(pScriptArguments);

            // requires the Selenium.JSPrefix and the CurrentComponent.GetQueryString
            return String.Format("{0}.selectItem(\"" + _selections[0] + "\");", pScriptArguments[1]);
        }

        public string SelectedItemsEvalMethod(params string[] pScriptArguments)
        {
            if (_selections.Count == 0)
                throw new ArgumentException("Specify AT LEAST one selection.");

            _ValidateScriptArguments(pScriptArguments);

            StringBuilder items = new StringBuilder();

            foreach (string item in SelectedItems())
                items.Append(String.Format("\"{0}\",", item));
           
            string script = String.Format(@"
var stringArray = {0}.createStringArray([{1}]);
{2}.selectItems(stringArray);"
            , pScriptArguments[0]
            , items.ToString().Substring(0, items.Length - 1)
            , pScriptArguments[1]
                );

            return script;
        }

        #endregion

        #region Validations

        private string _Validate(object pItem)
        {
            if (!(pItem is string))
                throw new ArgumentException("This component can only use Items that are strings.");

            return pItem as string;
        }

        private void _ValidateSingleSelection()
        {
            if (_selections.Count > 1 || _selections.Count == 0)
                throw new ArgumentException("Specify EXACTLY one selection.");
        }

        private void _ValidateScriptArguments(string[] pScriptArguments)
        {
            if (pScriptArguments.Length == 0)
                throw new ArgumentException("Please supply a valid script argument.");

            if (pScriptArguments.Length != 2)
                throw new ArgumentException("Supply EXACTLY 2 script arguments.");
        }

        #endregion

        #region Implementation of IHasCoordinates<List>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public List Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion

        #region Implementation of IHasSelectionValue

        public string GetSelectedRowQuery()
        {
            return GetSelectedIndexQuery();
        }

        public string GetSelectedIndexQuery()
        {
            return ".target.getSelectedIndex";
        }

        public string GetSelectedValueQuery()
        {
            return ".target.getSelectedValue";
        }

        public string GetSelectedItemQuery()
        {
            return GetSelectedValueQuery();
        }

        #endregion

        #region Implementation of IHasMultipleSelectionValues

        public string GetSelectedRowsQuery()
        {
            return GetSelectedIndicesQuery();
        }

        public string GetSelectedIndicesQuery()
        {
            return ".target.getSelectedIndices";
        }

        public string GetSelectedValuesQuery()
        {
            return ".target.getSelectedValues";
        }

        public string GetSelectedItemsQuery()
        {
            return GetSelectedValuesQuery();
        }

        #endregion
    }
}
