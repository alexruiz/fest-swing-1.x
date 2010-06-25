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
    public class ToggleButton : Component, IHasCoordinates<ToggleButton>
    {
        private const string TYPE_STRING = "toggleButton";
        private Coordinates _coordinates;

        public ToggleButton(string pName) : base(pName, TYPE_STRING)
        {}

        public ToggleButton(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasCoordinates<ToggleButton>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public ToggleButton Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion
    }
}
