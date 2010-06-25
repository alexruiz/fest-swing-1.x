// Date: Nov 4, 2009
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

namespace JavaSelenium.KeyStroke
{
    public abstract class KeyStrokeMappingProvider 
    {
        #region Public Constants

        // NOTE: These are bit masks

        public readonly int NO_MASK = 0x0;     // 0b0000

        public readonly int SHIFT_MASK = 0x1;  // 0b0001
        public readonly int CTRL_MASK = 0x2;   // 0b0010
        public readonly int META_MASK = 0x4;   // 0b0100
        public readonly int ALT_MASK = 0x8;    // 0b1000

        public readonly int SHIFT_DOWN_MASK = 0x40;
        public readonly int CTRL_DOWN_MASK = 0x80;
        public readonly int META_DOWN_MASK = 0x100;
        public readonly int ALT_DOWN_MASK = 0x200;

        #endregion
    }
}
