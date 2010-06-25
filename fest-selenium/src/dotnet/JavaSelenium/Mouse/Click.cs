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
    public static class Click
    {
        public static ClickInfo LeftButton()
        {
            return Button(ButtonConstants.LEFT_BUTTON);
        }

        public static ClickInfo RightButton()
        {
            return Button(ButtonConstants.RIGHT_BUTTON);
        }

        public static ClickInfo MiddleButton()
        {
            return Button(ButtonConstants.MIDDLE_BUTTON);
        }

        public static ClickInfo Button(int pButtonMask)
        {
            return new ClickInfo(pButtonMask, 1);   
        }
    }

    public class ClickInfo
    {
        private int _buttonMask;
        private int _times;

        public ClickInfo(int pButtonMask, int pTimes)
        {
            _buttonMask = pButtonMask;
            _times = pTimes;
        }

        public int ButtonMask
        {
            get { return _buttonMask; }
        }

        public int Times()
        {
            return _times;
        }

        public ClickInfo Times(int pTimes)
        {
            _times = pTimes;
            return this;
        }
    }
}
