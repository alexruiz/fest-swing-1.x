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

using JavaSelenium.Components;
using JavaSelenium.Components.Interfaces;
using JavaSelenium.KeyStroke;

namespace JavaSelenium
{
    public interface IJavaAction : ISeleniumOverrides, IRootComponentAware
    {
        Component CurrentComponent { get; set; }
        IJavaSelenium Selenium { get; }
        KeyStrokeMappingProvider_EN EnglishKeyStrokeProvider { get; }
        string Result { get; }

        IJavaAction SelectPopUp(Component pComponent);
        IJavaAction SelectPopUp();

        IJavaAction RightClick(Component pComponent);
        IJavaAction RightClick();

        bool IsEnabled(Component pComponent);

        IJavaAction WaitForNotBusy<T>(IHasTimeOut<T> pComponent) where T : Component;
        IJavaAction WaitForNotBusy<T>(IHasTimeOut<T> pComponent, JavaAction.WaitForNotBusyDelegate pDelegate) where T : Component;

        IJavaAction WaitForNotBusy<T>(string pTimeOut) where T : Component;
        IJavaAction WaitForNotBusy<T>(string pTimeOut, JavaAction.WaitForNotBusyDelegate pDelegate) where T : Component;

        IJavaAction WaitForNotBusy();
        IJavaAction WaitForNotBusy(JavaAction.WaitForNotBusyDelegate pDelegate);

        IJavaAction ScrollBrowserWindowToTop();
        IJavaAction ScrollBrowserWindowToBottom();
        IJavaAction ScrollBrowserWindowTo(int pHorizontalPosition, int pVerticalPosition);
    }
}
