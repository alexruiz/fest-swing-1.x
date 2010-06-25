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

using System.Collections.Generic;
using JavaSelenium.Components;
using JavaSelenium.Components.Interfaces;

namespace JavaSelenium
{
    public interface ISeleniumOverrides
    {
        IJavaAction AltKeyDown<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction AltKeyDown<T>(string pValue) where T : Component;
        IJavaAction AltKeyUp<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction AltKeyUp<T>(string pValue) where T : Component;

        IJavaAction ControlKeyDown<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction ControlKeyDown<T>(string pValue) where T : Component;
        IJavaAction ControlKeyUp<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction ControlKeyUp<T>(string pValue) where T : Component;

        // TODO : to be implemented in the future
        //IJavaAction DragAndDrop<T>(IHasCoordinates<T> pComponent) where T : Component;
        //IJavaAction DragAndDropToObject(pComponent pObjectToBeDragged, pComponent pDestinationObject);

        IJavaAction AddSelection<T>(IHasSelection<T> pComponent) where T : Component;
        IJavaAction AddSelection<T>(object pOption) where T : Component;

        IJavaAction Check(Component pComponent);
        IJavaAction Check();
        IJavaAction Uncheck(Component pComponent);
        IJavaAction Uncheck();

        // WARNING : These are not implemented due to the fact that Java applets have no
        //           default Confirmation dialogs. You must explicitly specify the dialog and 
        //           button components you would like to automate.
        //
        //IJavaAction ChooseCancelOnNextConfirmation();
        //IJavaAction ChooseOkOnNextConfirmation();

        IJavaAction Focus(Component pComponent);
        IJavaAction Focus();

        IJavaAction Click(Component pComponent);
        IJavaAction Click();

        IJavaAction ClickAt<T>(IHasCoordinates<T> pComponent) where T : Component;
        IJavaAction ClickAt<T>(Coordinates pCoordinates) where T : Component;

        IJavaAction DoubleClick(Component pComponent);
        IJavaAction DoubleClick();

        IJavaAction DoubleClickAt<T>(IHasCoordinates<T> pComponent) where T : Component;
        IJavaAction DoubleClickAt<T>(Coordinates pCoordinates) where T : Component;

        // WARNING: There is no equivalent for the GetSelectedID Selenium function
        //          against Java applets. Could throw exceptions when used, but
        //          probably best not to expose it them at all.
        //
        //string GetSelectedID(Component pComponent);
        //string GetSelectedID();

        // WARNING: There is not equivalent for the GetSelectedLabel Selenium function
        //          against Java applets. By default Swing component do not have associated
        //          labels unless explicitly created (via the JLabel.setLabelFor() method).
        //          Retrieving the associated label is even more cumbersome as there are
        //          no convenience methods and would require traversal of the Component
        //          hierarchy for each individual component. These methods are not exposed.
        //
        //string GetSelectedLabel(Component pComponent);
        //string GetSelectedLabel();

        int GetSelectedIndex(IHasSelectionValue pComponent);
        int GetSelectedIndex();

        List<int> GetSelectedIndices(IHasMultipleSelectionValues pComponent);
        List<int> GetSelectedIndices();

        // Note: These methods function identically against Java applets
        string GetSelectedValue(IHasSelectionValue pComponent);
        string GetSelectedValue();
        string GetValue(IHasSelectionValue pComponent);
        string GetValue();

        // Note: These methods function identically against Java applets
        List<string> GetSelectedValues(IHasMultipleSelectionValues pComponent);
        List<string> GetSelectedValues();
        List<string> GetValues(IHasMultipleSelectionValues pComponent);
        List<string> GetValues();

        string GetText(Component pComponent);
        string GetText();

        bool IsVisible(Component pComponent);

        IJavaAction KeyDown<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction KeyDown<T>(string pValue) where T : Component;
        IJavaAction KeyPress<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction KeyPress<T>(string pValue) where T : Component;
        IJavaAction KeyUp<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction KeyUp<T>(string pValue) where T : Component;

        IJavaAction MouseDown(Component pComponent);
        IJavaAction MouseDown();
        IJavaAction MouseDownAt<T>(IHasCoordinates<T> pComponent) where T : Component;
        IJavaAction MouseDownAt<T>(Coordinates pCoordinates) where T : Component;
        IJavaAction MouseMove(Component pComponent);
        IJavaAction MouseMove();
        IJavaAction MouseMoveAt<T>(IHasCoordinates<T> pComponent) where T : Component;
        IJavaAction MouseMoveAt<T>(Coordinates pCoordinates) where T : Component;
        IJavaAction MouseOut(Component pComponent);
        IJavaAction MouseOut();
        IJavaAction MouseOver(Component pComponent);
        IJavaAction MouseOver();
        IJavaAction MouseUp(Component pComponent);
        IJavaAction MouseUp();
        IJavaAction MouseUpAt<T>(IHasCoordinates<T> pComponent) where T : Component;
        IJavaAction MouseUpAt<T>(Coordinates pCoordinates) where T : Component;

        IJavaAction Select<T>(IHasSelection<T> pComponent) where T : Component;
        IJavaAction Select<T>(object pOption) where T : Component;
        IJavaAction SelectFrame(Component pComponent);
        IJavaAction SelectFrame();
        IJavaAction SelectWindow(Component pComponent);
        IJavaAction SelectWindow();

        IJavaAction ShiftKeyDown<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction ShiftKeyDown<T>(string pValue) where T : Component;
        IJavaAction ShiftKeyUp<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction ShiftKeyUp<T>(string pValue) where T : Component;

        IJavaAction Type<T>(IHasInputValue<T> pComponent) where T : Component;
        IJavaAction Type<T>(string pValue) where T : Component;

        // WARNING : These are not currently implemented due to limitations in the way FEST handles JPopupMenus.
        //
        //IJavaAction WaitForPopUp<T>(IHasTimeOut<T> pComponent) where T : Component;
        //IJavaAction WaitForPopUp<T>(string pTimeOut) where T : Component;
        //IJavaAction WaitForPopUp();
    }
}
