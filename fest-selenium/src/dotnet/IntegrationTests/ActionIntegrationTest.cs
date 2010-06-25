// Date: Sept 30 2009
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

// Note: This Test suite requires additional configuration:
//       - a webserver with the contents of the resource directory available in the web root directory.
//       - the latest versions of Internet Explorer, FireFox, Safari, and Opera
//       - the NUnit test runner.

using JavaSelenium;
using JavaSelenium.Components;
using NUnit.Framework;

namespace IntegrationTests
{
    [TestFixture]
    public class ActionIntegrationTest
    {
        private const int PORT = 4444;
        private const string URL = "http://localhost/applet.html";
        private const string HOST = "localhost";

        private const string TEST_APPLET_ID = "test_applet";
        private const string TABLE_APPLET_ID = "table_applet";
        private const string TREE_APPLET_ID = "tree_applet";
        private const string COMBO_APPLET_ID = "combo_applet";
        private const string TAB_APPLET_ID = "tab_applet";
        private const string SPINNER_APPLET_ID = "spinner_applet";
        private const string LIST_APPLET_ID = "list_applet";
        private const string CHECKBOX_APPLET_ID = "checkbox_applet";
        private const string POPUP_APPLET_ID = "popup_applet";

        [SetUp]
        public void SetUp() { }

        [TearDown]
        public void TearDown() { }

        private static IJavaSelenium _GetJavaSelenium(string pBrowserString, string pURL, string pObjectID)
        {
            return new JavaSelenium.JavaSelenium(HOST, PORT, pBrowserString, pURL, pObjectID);
        }

        #region Internet Explorer

        [Test]
        [Category("IE")]
        public void TestAltKeyDownIE()
        {
            _GenericTestAltKeyDown(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestAltKeyUpIE()
        {
            _GenericTestAltKeyUp(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestControlKeyDownIE()
        {
            _GenericTestControlKeyDown(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestControlKeyUpIE()
        {
            _GenericTestControlKeyUp(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestAddSelectionIE()
        {
            _GenericTestAddSelection(Browsers.IE.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestCheckIE()
        {
            _GenericTestCheck(Browsers.IE.String, CHECKBOX_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestUncheckIE()
        {
            _GenericTestUncheck(Browsers.IE.String, CHECKBOX_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestFocusIE()
        {
            _GenericTestFocus(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestClickIE()
        {
            _GenericTestClick(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestClickAtIE()
        {
            _GenericTestClickAt(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestDoubleClickIE()
        {
            _GenericTestDoubleClick(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestDoubleClickAtIE()
        {
            _GenericTestDoubleClickAt(Browsers.IE.String, TEST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("IE")]
        public void TestGetSelectedIDIE()
        {
            _GenericTestGetSelectedID(Browsers.IE.String, "");
        }*/

        [Test]
        [Category("IE")]
        public void TestGetSelectedIndexIE()
        {
            _GenericTestGetSelectedIndex(Browsers.IE.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestGetSelectedIndicesIE()
        {
            _GenericTestGetSelectedIndices(Browsers.IE.String, LIST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("IE")]
        public void TestGetSelectedLabelIE()
        {
            _GenericTestGetSelectedLabel(Browsers.IE.String, "");
        }*/

        [Test]
        [Category("IE")]
        public void TestGetSelectedValueIE()
        {
            _GenericTestGetSelectedValue(Browsers.IE.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestGetSelectedValuesIE()
        {
            _GenericTestGetSelectedValues(Browsers.IE.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestGetTextIE()
        {
            _GenericTestGetText(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestGetValueIE()
        {
            _GenericTestGetValue(Browsers.IE.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestGetValuesIE()
        {
            _GenericTestGetValues(Browsers.IE.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestKeyDownIE()
        {
            _GenericTestKeyDown(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestKeyPressIE()
        {
            _GenericTestKeyPress(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestKeyUpIE()
        {
            _GenericTestKeyUp(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestMouseDownIE()
        {
            _GenericTestMouseDown(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestMouseDownAtIE()
        {
            _GenericTestMouseDownAt(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestMouseMoveIE()
        {
            _GenericTestMouseMove(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestMouseMoveAtIE()
        {
            _GenericTestMouseMoveAt(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        [Ignore("Not Implemented in FEST")]
        public void TestMouseOutIE()
        {
            _GenericTestMouseOut(Browsers.IE.String, "");
        }

        [Test]
        [Category("IE")]
        public void TestMouseOverIE()
        {
            _GenericTestMouseOver(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestMouseUpIE()
        {
            _GenericTestMouseUp(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestMouseUpAtIE()
        {
            _GenericTestMouseUpAt(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestSelectIE()
        {
            _GenericTestSelect(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestSelectFrameIE()
        {
            _GenericTestSelectFrame(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestSelectWindowIE()
        {
            _GenericTestSelectWindow(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestSelectPopUpIE()
        {
            _GenericTestSelectPopUp(Browsers.IE.String);
        }

        [Test]
        [Category("IE")]
        public void TestIsEnabledIE()
        {
            _GenericTestIsEnabled(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestIsVisibleIE()
        {
            _GenericTestIsVisible(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestRightClickIE()
        {
            _GenericTestRightClick(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestShiftKeyDownIE()
        {
            _GenericTestShiftKeyDown(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestShiftKeyUpIE()
        {
            _GenericTestShiftKeyUp(Browsers.IE.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("IE")]
        public void TestTypeIE()
        {
            _GenericTestType(Browsers.IE.String, COMBO_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("IE")]
        public void TestWaitForPopUpIE()
        {
            _GenericTestWaitForPopUp(Browsers.IE.String, "");
        }*/

        [Test]
        [Category("IE")]
        public void TestWaitForNotBusyIE()
        {
            _GenericTestWaitForNotBusy(Browsers.IE.String, TEST_APPLET_ID);
        }

        #endregion

        #region FireFox

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestAltKeyDownFireFox()
        {
            _GenericTestAltKeyDown(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestAltKeyUpFireFox()
        {
            _GenericTestAltKeyUp(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestControlKeyDownFireFox()
        {
            _GenericTestControlKeyDown(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestControlKeyUpFireFox()
        {
            _GenericTestControlKeyUp(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestAddSelectionFireFox()
        {
            _GenericTestAddSelection(Browsers.FireFox.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestCheckFireFox()
        {
            _GenericTestCheck(Browsers.FireFox.String, TABLE_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestUncheckFireFox()
        {
            _GenericTestUncheck(Browsers.FireFox.String, TABLE_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestFocusFireFox()
        {
            _GenericTestFocus(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestClickFireFox()
        {
            _GenericTestClick(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestClickAtFireFox()
        {
            _GenericTestClickAt(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestDoubleClickFireFox()
        {
            _GenericTestDoubleClick(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestDoubleClickAtFireFox()
        {
            _GenericTestDoubleClickAt(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("FireFox")]
        public void TestGetSelectedIDFireFox()
        {
            _GenericTestGetSelectedID(Browsers.FireFox.String, "");
        }*/

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedIndexFireFox()
        {
            _GenericTestGetSelectedIndex(Browsers.FireFox.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedIndicesFireFox()
        {
            _GenericTestGetSelectedIndices(Browsers.FireFox.String, LIST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("FireFox")]
        public void TestGetSelectedLabelFireFox()
        {
            _GenericTestGetSelectedLabel(Browsers.FireFox.String, "");
        }*/

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedValueFireFox()
        {
            _GenericTestGetSelectedValue(Browsers.FireFox.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedValuesFireFox()
        {
            _GenericTestGetSelectedValues(Browsers.FireFox.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestGetTextFireFox()
        {
            _GenericTestGetText(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestGetValueFireFox()
        {
            _GenericTestGetValue(Browsers.FireFox.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestGetValuesFireFox()
        {
            _GenericTestGetValues(Browsers.FireFox.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestKeyDownFireFox()
        {
            _GenericTestKeyDown(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestKeyPressFireFox()
        {
            _GenericTestKeyPress(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestKeyUpFireFox()
        {
            _GenericTestKeyUp(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseDownFireFox()
        {
            _GenericTestMouseDown(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseDownAtFireFox()
        {
            _GenericTestMouseDownAt(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseMoveFireFox()
        {
            _GenericTestMouseMove(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseMoveAtFireFox()
        {
            _GenericTestMouseMoveAt(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseOutFireFox()
        {
            _GenericTestMouseOut(Browsers.FireFox.String, "");
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseOverFireFox()
        {
            _GenericTestMouseOver(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseUpFireFox()
        {
            _GenericTestMouseUp(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestMouseUpAtFireFox()
        {
            _GenericTestMouseUpAt(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestSelectFireFox()
        {
            _GenericTestSelect(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestSelectFrameFireFox()
        {
            _GenericTestSelectFrame(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestSelectWindowFireFox()
        {
            _GenericTestSelectWindow(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestSelectPopUpFireFox()
        {
            _GenericTestSelectPopUp(Browsers.FireFox.String);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestIsEnabledFireFox()
        {
            _GenericTestIsEnabled(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestIsVisibleFireFox()
        {
            _GenericTestIsVisible(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestRightClickFireFox()
        {
            _GenericTestRightClick(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestShiftKeyDownFireFox()
        {
            _GenericTestShiftKeyDown(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestShiftKeyUpFireFox()
        {
            _GenericTestShiftKeyUp(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestTypeFireFox()
        {
            _GenericTestType(Browsers.FireFox.String, COMBO_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestWaitForPopUpFireFox()
        {
            _GenericTestWaitForPopUp(Browsers.FireFox.String, "");
        }*/

        [Test]
        [Category("FireFox")]
        [Ignore("Not Implemented")]
        public void TestWaitForNotBusyFireFox()
        {
            _GenericTestWaitForNotBusy(Browsers.FireFox.String, TEST_APPLET_ID);
        }

        #endregion

        #region Safari

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestAltKeyDownSafari()
        {
            _GenericTestAltKeyDown(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestAltKeyUpSafari()
        {
            _GenericTestAltKeyUp(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestControlKeyDownSafari()
        {
            _GenericTestControlKeyDown(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestControlKeyUpSafari()
        {
            _GenericTestControlKeyUp(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestAddSelectionSafari()
        {
            _GenericTestAddSelection(Browsers.Safari.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestCheckSafari()
        {
            _GenericTestCheck(Browsers.Safari.String, TABLE_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestUncheckSafari()
        {
            _GenericTestUncheck(Browsers.Safari.String, TABLE_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestFocusSafari()
        {
            _GenericTestFocus(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestClickSafari()
        {
            _GenericTestClick(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestClickAtSafari()
        {
            _GenericTestClickAt(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestDoubleClickSafari()
        {
            _GenericTestDoubleClick(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestDoubleClickAtSafari()
        {
            _GenericTestDoubleClickAt(Browsers.Safari.String, TEST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("Safari")]
        public void TestGetSelectedIDSafari()
        {
            _GenericTestGetSelectedID(Browsers.Safari.String, "");
        }*/

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedIndexSafari()
        {
            _GenericTestGetSelectedIndex(Browsers.Safari.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedIndicesSafari()
        {
            _GenericTestGetSelectedIndices(Browsers.Safari.String, LIST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("Safari")]
        public void TestGetSelectedLabelSafari()
        {
            _GenericTestGetSelectedLabel(Browsers.Safari.String, "");
        }*/

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedValueSafari()
        {
            _GenericTestGetSelectedValue(Browsers.Safari.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedValuesSafari()
        {
            _GenericTestGetSelectedValues(Browsers.Safari.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestGetTextSafari()
        {
            _GenericTestGetText(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestGetValueSafari()
        {
            _GenericTestGetValue(Browsers.Safari.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestGetValuesSafari()
        {
            _GenericTestGetValues(Browsers.Safari.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestKeyDownSafari()
        {
            _GenericTestKeyDown(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestKeyPressSafari()
        {
            _GenericTestKeyPress(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestKeyUpSafari()
        {
            _GenericTestKeyUp(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseDownSafari()
        {
            _GenericTestMouseDown(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseDownAtSafari()
        {
            _GenericTestMouseDownAt(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseMoveSafari()
        {
            _GenericTestMouseMove(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseMoveAtSafari()
        {
            _GenericTestMouseMoveAt(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseOutSafari()
        {
            _GenericTestMouseOut(Browsers.Safari.String, "");
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseOverSafari()
        {
            _GenericTestMouseOver(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseUpSafari()
        {
            _GenericTestMouseUp(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestMouseUpAtSafari()
        {
            _GenericTestMouseUpAt(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestSelectSafari()
        {
            _GenericTestSelect(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestSelectFrameSafari()
        {
            _GenericTestSelectFrame(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestSelectWindowSafari()
        {
            _GenericTestSelectWindow(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestSelectPopUpSafari()
        {
            _GenericTestSelectPopUp(Browsers.Safari.String);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestIsEnabledSafari()
        {
            _GenericTestIsEnabled(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestIsVisibleSafari()
        {
            _GenericTestIsVisible(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestRightClickSafari()
        {
            _GenericTestRightClick(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestShiftKeyDownSafari()
        {
            _GenericTestShiftKeyDown(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestShiftKeyUpSafari()
        {
            _GenericTestShiftKeyUp(Browsers.Safari.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestTypeSafari()
        {
            _GenericTestType(Browsers.Safari.String, COMBO_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestWaitForPopUpSafari()
        {
            _GenericTestWaitForPopUp(Browsers.Safari.String, "");
        }*/

        [Test]
        [Category("Safari")]
        [Ignore("Not Implemented")]
        public void TestWaitForNotBusySafari()
        {
            _GenericTestWaitForNotBusy(Browsers.Safari.String, TEST_APPLET_ID);
        }

        #endregion

        #region Opera

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestAltKeyDownOpera()
        {
            _GenericTestAltKeyDown(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestAltKeyUpOpera()
        {
            _GenericTestAltKeyUp(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestControlKeyDownOpera()
        {
            _GenericTestControlKeyDown(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestControlKeyUpOpera()
        {
            _GenericTestControlKeyUp(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestAddSelectionOpera()
        {
            _GenericTestAddSelection(Browsers.Opera.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestCheckOpera()
        {
            _GenericTestCheck(Browsers.Opera.String, TABLE_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestUncheckOpera()
        {
            _GenericTestUncheck(Browsers.Opera.String, TABLE_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestFocusOpera()
        {
            _GenericTestFocus(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestClickOpera()
        {
            _GenericTestClick(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestClickAtOpera()
        {
            _GenericTestClickAt(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestDoubleClickOpera()
        {
            _GenericTestDoubleClick(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestDoubleClickAtOpera()
        {
            _GenericTestDoubleClickAt(Browsers.Opera.String, TEST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("Opera")]
        public void TestGetSelectedIDOpera()
        {
            _GenericTestGetSelectedID(Browsers.Opera.String, "");
        }*/

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedIndexOpera()
        {
            _GenericTestGetSelectedIndex(Browsers.Opera.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedIndicesOpera()
        {
            _GenericTestGetSelectedIndices(Browsers.Opera.String, LIST_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("Opera")]
        public void TestGetSelectedLabelOpera()
        {
            _GenericTestGetSelectedLabel(Browsers.Opera.String, "");
        }*/

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedValueOpera()
        {
            _GenericTestGetSelectedValue(Browsers.Opera.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestGetSelectedValuesOpera()
        {
            _GenericTestGetSelectedValues(Browsers.Opera.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestGetTextOpera()
        {
            _GenericTestGetText(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestGetValueOpera()
        {
            _GenericTestGetValue(Browsers.Opera.String, COMBO_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestGetValuesOpera()
        {
            _GenericTestGetValues(Browsers.Opera.String, LIST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestKeyDownOpera()
        {
            _GenericTestKeyDown(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestKeyPressOpera()
        {
            _GenericTestKeyPress(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestKeyUpOpera()
        {
            _GenericTestKeyUp(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseDownOpera()
        {
            _GenericTestMouseDown(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseDownAtOpera()
        {
            _GenericTestMouseDownAt(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseMoveOpera()
        {
            _GenericTestMouseMove(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseMoveAtOpera()
        {
            _GenericTestMouseMoveAt(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseOutOpera()
        {
            _GenericTestMouseOut(Browsers.Opera.String, "");
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseOverOpera()
        {
            _GenericTestMouseOver(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseUpOpera()
        {
            _GenericTestMouseUp(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestMouseUpAtOpera()
        {
            _GenericTestMouseUpAt(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestSelectOpera()
        {
            _GenericTestSelect(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestSelectFrameOpera()
        {
            _GenericTestSelectFrame(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestSelectWindowOpera()
        {
            _GenericTestSelectWindow(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestSelectPopUpOpera()
        {
            _GenericTestSelectPopUp(Browsers.Opera.String);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestIsEnabledOpera()
        {
            _GenericTestIsEnabled(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestIsVisibleOpera()
        {
            _GenericTestIsVisible(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestRightClickOpera()
        {
            _GenericTestRightClick(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestShiftKeyDownOpera()
        {
            _GenericTestShiftKeyDown(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestShiftKeyUpOpera()
        {
            _GenericTestShiftKeyUp(Browsers.Opera.String, TEST_APPLET_ID);
        }

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestTypeOpera()
        {
            _GenericTestType(Browsers.Opera.String, COMBO_APPLET_ID);
        }

        // See ISeleniumOverrides
        /*[Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestWaitForPopUpOpera()
        {
            _GenericTestWaitForPopUp(Browsers.Opera.String, "");
        }*/

        [Test]
        [Category("Opera")]
        [Ignore("Not Implemented")]
        public void TestWaitForNotBusyOpera()
        {
            _GenericTestWaitForNotBusy(Browsers.Opera.String, TEST_APPLET_ID);
        }

        #endregion

        #region Private Methods

        private void _OpenPage(IJavaSelenium javaSelenium)
        {
            javaSelenium.Selenium.Open(URL);
            javaSelenium.Selenium.WaitForPageToLoad("10000");
            javaSelenium.Selenium.WindowMaximize();
        }

        private void _GenericTestAltKeyDown(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("a");

                // Require focus before keypress
                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.AltKeyDown(button));
            }
        }

        private void _GenericTestAltKeyUp(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("a");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.AltKeyUp(button));
            }
        }

        private void _GenericTestControlKeyDown(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("a");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.ControlKeyDown(button));
            }
        }

        private void _GenericTestControlKeyUp(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("a");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.ControlKeyUp(button));
            }
        }

        private void _GenericTestAddSelection(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);

                // applet must be visible to be able to run tests against.
                action.ScrollBrowserWindowToBottom();

                List list = new List("employees").AddItems("Debbie Scott", "Scott Hommel");

                Assert.AreEqual(action, action.Focus(list).Click());
                Assert.AreEqual(action, action.AddSelection(list));
                
                Assert.AreEqual(2, action.GetSelectedValues(list).Count);
                Assert.AreEqual("Debbie Scott", action.GetSelectedValues()[0]);
                Assert.AreEqual("Scott Hommel", action.GetSelectedValues()[1]);
                
            }
        }

        private void _GenericTestCheck(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                CheckBox chin = new CheckBox("Chin");
                CheckBox glasses = new CheckBox("Glasses");
                CheckBox hair = new CheckBox("Hair");
                CheckBox teeth = new CheckBox("Teeth");

                action.ScrollBrowserWindowToBottom();

                Assert.AreEqual(action, action.Check(chin).Check(glasses).Check(hair).Check(teeth));
            }
        }

        private void _GenericTestUncheck(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                CheckBox chin = new CheckBox("Chin");
                CheckBox glasses = new CheckBox("Glasses");
                CheckBox hair = new CheckBox("Hair");
                CheckBox teeth = new CheckBox("Teeth");

                action.ScrollBrowserWindowToBottom();

                Assert.AreEqual(action, action.Check(chin).Uncheck(chin)
                                        .Check(glasses).Uncheck(glasses)
                                        .Check(hair).Uncheck(hair)
                                        .Check(teeth).Uncheck(teeth)
                                        );
            }
        }

        private void _GenericTestFocus(string pBrowserString)
        {
            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(HOST, PORT, pBrowserString, URL))
            {
                javaSelenium.ObjectID = TEST_APPLET_ID;
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Assert.AreEqual(action, action.Focus());

                javaSelenium.ObjectID = TABLE_APPLET_ID;

                action = new JavaAction(javaSelenium);
                Assert.AreEqual(action, action.Focus());

                javaSelenium.ObjectID = TREE_APPLET_ID;

                action = new JavaAction(javaSelenium);
                Assert.AreEqual(action, action.Focus());

                javaSelenium.ObjectID = COMBO_APPLET_ID;

                action = new JavaAction(javaSelenium);
                Assert.AreEqual(action, action.Focus());

                javaSelenium.ObjectID = TAB_APPLET_ID;

                action = new JavaAction(javaSelenium);
                Assert.AreEqual(action, action.Focus());

                javaSelenium.ObjectID = SPINNER_APPLET_ID;

                action = new JavaAction(javaSelenium);
                Assert.AreEqual(action, action.Focus());

                javaSelenium.ObjectID = LIST_APPLET_ID;

                action = new JavaAction(javaSelenium);
                Assert.AreEqual(action, action.ScrollBrowserWindowToBottom());
                Assert.AreEqual(action, action.Focus());

                Button hire = new Button("hire");
                Button fire = new Button("fire");
                List employees = new List("employees");
                TextBox employeeName = new TextBox("employeeName");

                // WARNING : An illegal state exception will be thrown when attempting to focus on a disabled component
                //Assert.AreEqual(action, action.Focus(hire));
                Assert.AreEqual(action, action.Focus(fire));
                Assert.AreEqual(action, action.Focus(employees));

                // Enter something in the employeeName text field to enable the hire button
                Assert.AreEqual(action, action.Focus(employeeName).Click().Type<TextBox>("New Guy"));
                Assert.AreEqual("New Guy", action.Focus(hire).Click().GetSelectedValue(employees));
                Assert.AreEqual(1, action.GetSelectedValues(employees).Count);
            }
        }

        private void _GenericTestClick(string pBrowserString)
        {
            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(HOST, PORT, pBrowserString, URL))
            {
                javaSelenium.ObjectID = TEST_APPLET_ID;
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual(action, action.Click(button));

                javaSelenium.ObjectID = TREE_APPLET_ID;
                Tree tree = new Tree("tree");
                action = new JavaAction(javaSelenium);

                Assert.AreEqual(action, action.Focus(tree).Select<Tree>("root").Click()
                                            .Select<Tree>("root/attribute1").Click()
                                            .Select<Tree>("root/attribute1/attribute1.1").Click()
                                            .Select<Tree>("root/attribute1/attribute1.3/leaf1.3.1").Click()
                                            .Select<Tree>("root/attribute1/attribute1.3/leaf1.3.2").Click()
                                            .Select<Tree>("root/attribute1/attribute1.3/leaf1.3.3").Click()
                                            .Select<Tree>("root/attribute2").Click()
                                            .Select<Tree>("root/attribute3").Click()
                    );

                javaSelenium.ObjectID = COMBO_APPLET_ID;
                ComboBox pattern = new ComboBox("pattern");
                action = new JavaAction(javaSelenium);

                Assert.AreEqual(action, action.Focus(pattern)
                                            .Select<ComboBox>("yyyy.MMMMM.dd GGG hh:mm aaa")
                                            .Select<ComboBox>("K:mm a,z")
                                            .Select<ComboBox>("H:mm:ss:SSS")
                                            .Select<ComboBox>("h:mm a")
                                            .Select<ComboBox>("EEE, MMM d, ''yy")
                                            .Select<ComboBox>("yyy.MM.dd G 'at' hh:mm:ss z")
                                            .Select<ComboBox>("MM/dd/yy")
                    );

                javaSelenium.ObjectID = TAB_APPLET_ID;
                TabbedPane pane = new TabbedPane("pane");
                action = new JavaAction(javaSelenium);

                Assert.AreEqual(action, action.Focus(pane)
                                            .Select<TabbedPane>("Tab 1").Click()
                                            .Select<TabbedPane>("Tab 2").Click()
                                            .Select<TabbedPane>("Tab 3").Click()
                                            .Select<TabbedPane>("Tab 4").Click()
                    );

                javaSelenium.ObjectID = SPINNER_APPLET_ID;
                Spinner month = new Spinner("month");
                Spinner year = new Spinner("year");
                Spinner date = new Spinner("date");

                Assert.AreEqual(action, action
                                            .Focus(month).Click()
                                            .Focus(year).Click()
                                            .Focus(date).Click()
                    );
            }
        }

        private void _GenericTestClickAt(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(10,10));

                Assert.AreEqual(action, action.ClickAt(button));
            }
        }

        private void _GenericTestDoubleClick(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual(action, action.DoubleClick(button));
            }
        }

        private void _GenericTestDoubleClickAt(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(10, 10));

                Assert.AreEqual(action, action.DoubleClickAt(button));
            }
        }

        private void _GenericTestGetSelectedIndex(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                ComboBox box = new ComboBox("pattern");

                Assert.AreEqual(0, action.GetSelectedIndex(box));
            }
        }

        private void _GenericTestGetSelectedIndices(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                List list = new List("employees");

                Assert.AreEqual(0, action.GetSelectedIndices(list)[0]);
                Assert.AreEqual(1, action.GetSelectedIndices(list).Count);
            }
        }

        private void _GenericTestGetSelectedValue(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                ComboBox box = new ComboBox("pattern");

                Assert.AreEqual("dd MMMMM yyyy", action.GetSelectedValue(box));
            }
        }

        private void _GenericTestGetSelectedValues(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                List list = new List("employees");

                Assert.AreEqual("Debbie Scott", action.GetSelectedValues(list)[0]);
                Assert.AreEqual(1, action.GetSelectedValues(list).Count);
            }
        }

        private void _GenericTestGetText(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual("Click Me", action.GetText(button));
            }
        }

        private void _GenericTestGetValue(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                ComboBox box = new ComboBox("pattern");

                Assert.AreEqual("dd MMMMM yyyy", action.GetValue(box));
            }
        }

        private void _GenericTestGetValues(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                List list = new List("employees");

                Assert.AreEqual("Debbie Scott", action.GetSelectedValues(list)[0]);
                Assert.AreEqual(1, action.GetSelectedValues(list).Count);
            }
        }

        private void _GenericTestKeyDown(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("a");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.KeyDown(button));
            }
        }

        private void _GenericTestKeyPress(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("b");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.KeyPress(button));
            }
        }

        private void _GenericTestKeyUp(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("c");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.KeyUp(button));
            }
        }

        private void _GenericTestMouseDown(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseDown(button));
            }
        }

        private void _GenericTestMouseDownAt(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseDownAt(button));
            }
        }

        private void _GenericTestMouseMove(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseMove(button));
            }
        }

        private void _GenericTestMouseMoveAt(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseDownAt(button));
            }
        }

        private void _GenericTestMouseOut(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseOut(button));
            }
        }

        private void _GenericTestMouseOver(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseOver(button));
            }
        }

        private void _GenericTestMouseUp(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseUp(button));
            }
        }

        private void _GenericTestMouseUpAt(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Coordinates(new Coordinates(40, 40));

                Assert.AreEqual(action, action.MouseUpAt(button));
            }
        }

        private void _GenericTestSelect(string pBrowserString)
        {
            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(HOST, PORT, pBrowserString, URL))
            {
                javaSelenium.ObjectID = COMBO_APPLET_ID;
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                ComboBox box = new ComboBox("pattern").AddItem("dd.MM.yy");

                Assert.AreEqual(action, action.Select(box));

                javaSelenium.ObjectID = TABLE_APPLET_ID;
                action = new JavaAction(javaSelenium);

                // Alternative syntax for prespecifying selection items.
                //Table table = new Table("table").AddItems(new Cell(0, 0), new Cell(0,1), new Cell(0,2), new Cell(0,3), new Cell(0,4));
                Table table = new Table("table");

                Assert.AreEqual(action, action.Focus(table)
                                            .Select<Table>(new Cell(0,0))
                                            //    .Select<Table>(new Cell(0,1)) // this cell is avoided to prevent the popup dialog from hanging the test.
                                            .Select<Table>(new Cell(0,2))
                                            .Select<Table>(new Cell(0,3))
                                            .Select<Table>(new Cell(0,4))
                    );
            }

            _GenericTestDialogFocus(pBrowserString);
        }

        // WARNING: This test runs extremely slow
        private void _GenericTestDialogFocus(string pBrowserString)
        {
            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(HOST, PORT, pBrowserString, URL))
            {
                _OpenPage(javaSelenium);

                javaSelenium.ObjectID = TABLE_APPLET_ID;
                JavaAction action = new JavaAction(javaSelenium);

                Table table = new Table("table");

                Dialog dialog = new Dialog("dialog");
                TabbedPane pane = new TabbedPane("ColorChooser.tabPane");

                //System.Diagnostics.Debugger.Break();

                Assert.AreEqual(action, action.Focus(table)
                    .Select<Table>(new Cell(0, 1))
                    .Focus(dialog)                      // focuses on the actual dialog component
                    .SetRootComponent(dialog)
                    .Focus(pane)                
                    .Select<TabbedPane>("Swatches").Click() // subsequent calls inherit the RootComponent
                    .Select<TabbedPane>("HSB").Click()
                    .Select<TabbedPane>("RGB").Click()
                    );
            }
        }

        private void _GenericTestSelectFrame(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual(action, action.SelectFrame(button));
            }
        }

        private void _GenericTestSelectWindow(string pBrowserString , string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual(action, action.SelectWindow(button));
            }
        }

        // WARNING: Broken
        private void _GenericTestSelectPopUp(string pBrowserString)
        {
            using (IJavaSelenium javaSelenium = new JavaSelenium.JavaSelenium(HOST, PORT, pBrowserString, URL))
            {
                _OpenPage(javaSelenium);

                javaSelenium.ObjectID = POPUP_APPLET_ID;

                JavaAction action = new JavaAction(javaSelenium);
                TextBox text = new TextBox("text");

                //MenuItem item1 = new MenuItem("item1");
                //MenuItem item2 = new MenuItem("item2");

                action.ScrollBrowserWindowToBottom();

                // WARNING: Must RightClick before the SelectPopup can be called.
                //          This functionality is broken at the moment. There currently
                //          is no way in FEST to specify a JPopupMenu directly 
                Assert.AreEqual(action, action.Focus(text).RightClick().SelectPopUp());

                //.RightClick().Select<MenuItem>(item1).Select<MenuItem>(item2)
            }
        }

        private void _GenericTestIsEnabled(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual(true, action.IsEnabled(button));
            }
        }

        private void _GenericTestIsVisible(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual(true, action.IsVisible(button));
            }
        }

        private void _GenericTestRightClick(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme");

                Assert.AreEqual(action, action.RightClick(button));
            }
        }

        private void _GenericTestShiftKeyDown(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("a");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.ShiftKeyDown(button));
            }
        }

        private void _GenericTestShiftKeyUp(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                Button button = new Button("clickme").Value("b");

                Assert.AreEqual(action, action.Focus(button).Click());
                Assert.AreEqual(action, action.ShiftKeyUp(button));
            }
        }

        private void _GenericTestType(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                ComboBox box = new ComboBox("pattern").Value("Feb");

                Assert.AreEqual(action, action.Focus(box).Click());
                Assert.AreEqual(action, action.Type(box));
            }
        }

        // See ISeleniumOverrides
        /*private void _GenericTestWaitForPopUp(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                Assert.Fail("Not Implemented");
            }
        }*/

        private void _GenericTestWaitForNotBusy(string pBrowserString, string pAppletID)
        {
            using (IJavaSelenium javaSelenium = _GetJavaSelenium(pBrowserString, URL, pAppletID))
            {
                _OpenPage(javaSelenium);

                JavaAction action = new JavaAction(javaSelenium);
                JavaAction.WaitForNotBusyDelegate pDelegate = _WaitForNotBusyImplementation;

                Assert.AreEqual(action, action.WaitForNotBusy(pDelegate));
            }
        }

        private static string _WaitForNotBusyImplementation()
        {
            // This should call a javascript method on the page which returns when the page is no longer busy.
            return "OK";
        }

        #endregion
    }
}
