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

using System;
using Selenium;

namespace JavaSelenium
{
    public class JavaSelenium : IJavaSelenium
    {
        private readonly ISelenium _selenium;
        private string _objectID;
        private string _jsPrefix;

        #region Constructors

        public JavaSelenium(ICommandProcessor pProcessor)
            : this(new DefaultSelenium(pProcessor), String.Empty)
        {}
       
        public JavaSelenium(string pHost, int pPort, string pBrowserString, string pUrl) 
            : this(new DefaultSelenium(pHost, pPort, pBrowserString, pUrl), String.Empty)
        {}

        public JavaSelenium(string pHost, int pPort, string pBrowserString, string pUrl, string pObjectID)
            :this (new DefaultSelenium(pHost, pPort, pBrowserString, pUrl), pObjectID)
        {}

        public JavaSelenium(ISelenium pSelenium)
            : this(pSelenium, String.Empty)
        {}

        public JavaSelenium(ISelenium pSelenium, string pObjectID)
        {
            _selenium = pSelenium;
            _objectID = pObjectID;

            _selenium.Start();
            _SetJSPrefix();
        }
        #endregion

        #region Public Properties
        public string ObjectID {
            get { return _objectID; }
            set
            {
                _objectID = value;
                _SetJSPrefix();
            }
        }

        public string JSPrefix {
            get { return _jsPrefix; }
            set { _jsPrefix = value; }  
        }

        public ISelenium Selenium
        {
            get { return _selenium; }
        }
        #endregion

        public string GetEval(string pScript)
        {
            return Selenium.GetEval(pScript);
        }

        public string InvokeMethod(string pFunctionName, params object[] pParameters)
        {
            return Selenium.GetEval(_CreateJSForMethodInvocation(pFunctionName, pParameters));
        }

        public string GetField(string pFieldName)
        {
            return Selenium.GetEval(_CreateJSForGetField(pFieldName));
        }

        public string SetField(string pFieldName, string pValue)
        {
            return Selenium.GetEval(_CreateJSForSetField(pFieldName, pValue));
        }

        public string SetField(string pFieldName, int pValue)
        {
            return Selenium.GetEval(_CreateJSForSetField(pFieldName, pValue));
        }

        public void Dispose()
        {
            Selenium.Stop();
        }

        #region Protected

        protected string _CreateJSForGetField(string pFieldName)
        {
            return _jsPrefix + pFieldName + ";";
        }

        protected string _CreateJSForSetField(string pFieldName, object pValue)
        {
            if (pValue is string)
                return _jsPrefix + pFieldName + " = \"" + (string)pValue + "\";";
            
            if (pValue is int)
                return _jsPrefix + pFieldName + " = " + (int)pValue + ";";

            throw new ApplicationException("Only string or int primitive object types can be assigned to a field.");
        }

        protected string _CreateJSForMethodInvocation(string pFunction, params object[] pParameters)
        {
            string arguments = String.Empty;

            if (pParameters.Length == 0)
                return _jsPrefix + pFunction + "();";

            foreach (object argument in pParameters)
            {
                if (argument is string)
                    arguments = arguments + "\"" + argument + "\",";
                else if (argument is int)
                    arguments = arguments + argument + ",";
                else
                    throw new ApplicationException(
                        "Can only supply primitive types of string and int as parameters to InvokeMethod()");
            }
            
            // truncate last comma in arguments
            arguments = arguments.Substring(0, arguments.Length - 1);

            return _jsPrefix + pFunction + "(" + arguments + ");";
        }

        protected void _SetJSPrefix()
        {
            string userAgent = Selenium.GetEval("navigator.userAgent");

            if ( !String.IsNullOrEmpty(userAgent) )
            {
                if (userAgent.Contains(Browsers.FireFox.Constant) || userAgent.Contains(Browsers.IE.Constant) ||
                userAgent.Contains(Browsers.Opera.Constant) || userAgent.Contains(Browsers.Safari.Constant))
                {
                    //JSPrefix = "window.document['" + ObjectID + "'].";
                    _jsPrefix = String.Format("window.document.getElementById(\"{0}\").", ObjectID);
                }
                else
                    //JSPrefix = "document['" + ObjectID + "'].";
                    _jsPrefix = String.Format("document.getElementById(\"{0}\").", ObjectID);
            } 
        }

        #endregion
    }
}
