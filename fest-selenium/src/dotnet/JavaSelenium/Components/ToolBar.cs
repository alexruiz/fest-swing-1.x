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
using JavaSelenium.Components.Interfaces;

namespace JavaSelenium.Components
{
    public class ToolBar : Component, IHasCoordinates<ToolBar>
    {
        private const string TYPE_STRING = "toolBar";
        private Coordinates _coordinates;

        public ToolBar(string pName) : base(pName, TYPE_STRING)
        {}

        public ToolBar(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasCoordinates<ToolBar>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public ToolBar Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion
    }
}
