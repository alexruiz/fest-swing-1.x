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
using JavaSelenium.Components.Interfaces;

namespace JavaSelenium.Components
{
    public class TextBox : Component, IHasInputValue<TextBox>, IHasSelection<TextBox>, IHasCoordinates<TextBox>
    {
        private const string TYPE_STRING = "textBox";
        private string _value;
        private readonly List<string> _selections = new List<string>();
        private Coordinates _coordinates;

        public TextBox(string pName) : base(pName, TYPE_STRING)
        {}

        public TextBox(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasInputValue<TextBox>

        public string Value()
        {
            if (_value == null)
                _value = String.Empty;
            return _value;
        }

        public TextBox Value(string pValue)
        {
            _value = pValue;
            return this;
        }

        #endregion

        #region Implementation of IHasSelection<TextBox>

        public string SelectedItem()
        {
            _ValidateSingleSelection();
            return _selections[0];
        }

        public List<string> SelectedItems()
        {
            _ValidateSingleSelection();
            return _selections;
        }

        public TextBox AddItem(object pItem)
        {
            // Ensure that there is only ever one entry in the List<string> selections array
            _selections.Clear();
            _selections.Add(_Validate(pItem));
            return this;
        }

        public TextBox AddItems(params object[] pItems)
        {
            if (pItems.Length == 1 && _selections.Count == 0)
                return AddItem(pItems[0]);

            throw new ArgumentException("This component can only have a single item at a time. Use AddItem()");
        }

        public TextBox RemoveItem(object pItem)
        {
            if (!_selections.Remove(_Validate(pItem)))
                throw new ArgumentException("Attempt to remove a non-existent item.");
            return this;
        }

        public TextBox RemoveItems(params object[] pItems)
        {
            throw new ArgumentException("This component can only have a single item at a time. Use RemoveItem().");
        }

        public string SelectedItemEvalMethod(params string [] pScriptArguments)
        {
            _ValidateSingleSelection();
            _ValidateScriptArguments(pScriptArguments);

            // requires the Selenium.JSPrefix and the CurrentComponent.GetQueryString
            return String.Format("{0}.select(\"" + _selections[0] + "\");", pScriptArguments[0]);
        }

        public string SelectedItemsEvalMethod(params string[] pScriptArguments)
        {
            return SelectedItemEvalMethod(pScriptArguments);
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

            if (pScriptArguments.Length > 1)
                throw new ArgumentException("Too many script arguments. Only one is required.");
        }

        #endregion

        #region Implementation of IHasCoordinates<TextBox>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public TextBox Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion
    }
}
