// Date: Nov 10, 2009
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

namespace JavaSelenium.Mouse
{
    /// <summary>
    /// These constants map to java.awt.event.InputEvent constants for
    /// mouse buttons and may be subject to change with newer releases of
    /// Sun Microsystems's Java.
    /// 
    /// WARNING: the System.Windows.Input.Keys does NOT map these values to
    ///          the same values expected by Java.
    /// </summary>
    public static class ButtonConstants
    {
        public static readonly int BUTTON1_MASK = 16;
        public static readonly int BUTTON2_MASK = 8;
        public static readonly int BUTTON3_MASK = 4;

        public static readonly int LEFT_BUTTON = BUTTON1_MASK;
        public static readonly int MIDDLE_BUTTON = BUTTON2_MASK;
        public static readonly int RIGHT_BUTTON = BUTTON3_MASK;
    }
}
