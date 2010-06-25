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
    public class FileChooser : Component, IHasSelection<FileChooser>, IHasCoordinates<FileChooser>, IRequiresJavaObjectCreation
    {
        private const string TYPE_STRING = "fileChooser";
        private readonly List<string> _selections = new List<string>();
        private Coordinates _coordinates;

        public FileChooser(string pName) : base(pName, TYPE_STRING)
        {}

        public FileChooser(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasSelection<FileChooser>

        public string SelectedItem()
        {
            _ValidateSingleSelection();
            return _selections[0];
        }

        public List<string> SelectedItems()
        {
            return _selections;
        }

        public FileChooser AddItem(object pItem)
        {
            _selections.Add(_ValidateFilePath(pItem));
            return this;
        }

        public FileChooser AddItems(params object[] pItems)
        {
            foreach (object item in pItems)
                _selections.Add(_ValidateFilePath(item));
            return this;
        }

        public FileChooser RemoveItem(object pItem)
        {
            if (!_selections.Remove(_ValidateFilePath(pItem)))
                throw new ArgumentException("Attempt to remove a non-existent item.");
            return this;
        }

        public FileChooser RemoveItems(params object[] pItems)
        {
            foreach (object item in pItems)
            {
                if (!_selections.Remove(_ValidateFilePath(item)))
                    throw new ArgumentException("Attempt to remove a non-existent item.");
            }
            return this;  
        }

        public string SelectedItemEvalMethod(params string[] pScriptArguments)
        {
            _ValidateSingleSelection();
            _ValidateScriptArguments(pScriptArguments);

            string script = String.Format(@"
var file = {0}.createFile({1});
{2}.selectFile(file);"
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
var files = {0}.createFiles({1});
{2}.selectFiles(files);"
    , pScriptArguments[0] //the Selenium.JSPrefix and _GetQueryBase
    , items.ToString().Substring(0, items.Length - 1) + "]"
    , pScriptArguments[1] //the Selenium.JSPrefix and the CurrentComponent.GetQueryString()
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

        private string _ValidateFilePath(object pItem)
        { 
            string path = _Validate(pItem);

            string pattern = @"^(([a-zA-Z]\:)|(\\))(\\{1}|((\\{1})[^\\]([^/:*?<>""|]*))+)$";
            Regex reg = new Regex(pattern, RegexOptions.Compiled | RegexOptions.IgnoreCase);

            if (! reg.IsMatch(path))
                throw new ArgumentException("This component requires a valid file/directory path.");
            
            return path;
        }

        private void _ValidateScriptArguments(string[] pScriptArguments)
        {
            if (pScriptArguments.Length == 0)
                throw new ArgumentException("Please supply 2 valid script arguments.");

            if (pScriptArguments.Length != 2)
                throw new ArgumentException("Supply EXACTLY 2 script arguments.");
        }

        #endregion

        #region Implementation of IHasCoordinates<FileChooser>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public FileChooser Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion
    }
}
