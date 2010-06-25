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
using Selenium;

namespace JavaSelenium
{
    public interface IJavaSelenium : IDisposable
    {
        string ObjectID { get; set; }
        ISelenium Selenium { get; }
        string JSPrefix { get; set; }
        string GetEval(string pScript);
        string InvokeMethod(string pFunctionName, params object[] pParameters);
        string GetField(string pFieldName);
        string SetField(string pFieldName, string pValue);
        string SetField(string pFieldName, int pValue);
    }
}
