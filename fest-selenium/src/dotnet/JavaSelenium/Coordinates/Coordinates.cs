// Date: Nov 9 2009
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

namespace JavaSelenium
{
    public class Coordinates
    {
        private readonly int _x;
        private readonly int _y;

        public Coordinates(int pX, int pY)
        {
            _x = pX;
            _y = pY;
        }

        public int X
        {
            get { return _x; }
        }

        public int Y
        {
            get { return _y; }
        }

        public new bool Equals(object pComparison)
        {
            return (pComparison != null
                    && pComparison is Coordinates
                    && ((Coordinates) pComparison).X == _x
                    && ((Coordinates) pComparison).Y == _y);
        }
    }
}
