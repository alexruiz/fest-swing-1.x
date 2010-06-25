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

using JavaSelenium.Components.Interfaces;

namespace JavaSelenium.Components
{
    public class ScrollPane : Component, IHasCoordinates<ScrollPane>
    {
        private const string TYPE_STRING = "scrollPane";
        private Coordinates _coordinates;

        public ScrollPane(string pName) : base(pName, TYPE_STRING)
        {}

        public ScrollPane(string pName, string pText) : base(pName, pText, TYPE_STRING)
        {}

        #region Implementation of IHasCoordinates<ScrollPane>

        public Coordinates Coordinates()
        {
            if (_coordinates == null)
                _coordinates = new Coordinates(0, 0);
            return _coordinates;
        }

        public ScrollPane Coordinates(Coordinates pCoordinates)
        {
            _coordinates = pCoordinates;
            return this;
        }

        #endregion
    }
}
