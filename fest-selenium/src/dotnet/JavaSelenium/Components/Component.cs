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

namespace JavaSelenium.Components
{
    public abstract class Component
    {
        private readonly string _type;
        private readonly string _name;
        private string _text;

        protected Component(string pName, string pText, string pType)
        {
            _name = pName;
            _type = pType;
            _text = pText;
        }

        protected Component(string pName, string pType): this(pName, String.Empty, pType)
        {}

        public string Type
        {
            get { return _type; }
        }

        public string Name
        {
            get { return _name; }
        }

        public string Text
        {
            get
            {
                if (_text == null)
                    _text = String.Empty;
                return _text;
            }
            set { _text = value; }
        }

        public string GetBaseComponentString()
        {
            return String.Format("{0}(\"{1}\")", _type, _name);
        }

        public string GetQueryString()
        {
            return String.Format("getTestFixture().{0}", GetBaseComponentString());
        }

        public string GetQueryString(string pPrefix)
        {
            return (pPrefix != String.Empty) 
                ? String.Format("getTestFixture().{0}.{1}", pPrefix, GetBaseComponentString()) 
                : GetQueryString();
        }
    }
}
