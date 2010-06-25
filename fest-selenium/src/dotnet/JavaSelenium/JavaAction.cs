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

using System;
using System.Collections.Generic;
using System.Text;
using JavaSelenium.Components;
using JavaSelenium.Components.Interfaces;
using JavaSelenium.KeyStroke;

namespace JavaSelenium
{
    public class JavaAction : IJavaAction
    {
        private readonly IJavaSelenium _selenium;
        private readonly KeyStrokeMappingProvider_EN _provider_EN;
        private string _result;
        private int INVALID_INDEX = -1;
        private IRootComponent[] _rootComponents;
        private Component _currentComponent;

        public delegate string WaitForNotBusyDelegate();

        private readonly WaitForNotBusyDelegate _delegate;

        #region Constructors

        public JavaAction(IJavaSelenium pSelenium)
        {
            _selenium = pSelenium;
            _provider_EN = new KeyStrokeMappingProvider_EN();
        }

        public JavaAction(IJavaSelenium pSelenium, WaitForNotBusyDelegate pDelegate) : this(pSelenium)
        {
            _delegate = pDelegate;
        }

        #endregion

        #region Properties

        public WaitForNotBusyDelegate WaitDelegate
        {
            get
            {
                if (_delegate == null)
                    throw new ApplicationException(
                        "Cannot use WaitForNotBusy without specifying a delegate. Either supply one with the constructor or use the overloaded method.");

                return _delegate;
            }
        }

        public Component CurrentComponent
        {
            get { return _currentComponent; }
            set { _currentComponent = value; } 
        }

        public IJavaSelenium Selenium
        {
            get { return _selenium; }
        }

        public KeyStrokeMappingProvider_EN EnglishKeyStrokeProvider
        {
            get { return _provider_EN; }
        }

        public string Result
        {
            get
            {
                if (_result == null)
                    _result = String.Empty;
                return _result;
            }
        }

        #endregion

        #region Implementation of IJavaAction

        public IJavaAction AltKeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _AltKeyDown(pComponent);
            return this;
        }

        public IJavaAction AltKeyDown<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>)CurrentComponent;
                component.Value(pValue);
                _AltKeyDown(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        public IJavaAction AltKeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _AltKeyUp(pComponent);
            return this;
        }

        public IJavaAction AltKeyUp<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>) CurrentComponent;
                component.Value(pValue);
                _AltKeyUp(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        public IJavaAction ControlKeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _ControlKeyDown(pComponent);
            return this;
        }

        public IJavaAction ControlKeyDown<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>) CurrentComponent;
                component.Value(pValue);
                _ControlKeyDown(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        public IJavaAction ControlKeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _ControlKeyUp(pComponent);
            return this;
        }

        public IJavaAction ControlKeyUp<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>) CurrentComponent;
                component.Value(pValue);
                _ControlKeyUp(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        public IJavaAction AddSelection<T>(IHasSelection<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _AddSelection(pComponent);
            return this;
        }

        public IJavaAction AddSelection<T>(object pOption) where T : Component
        {
            try
            {
                IHasSelection<T> component = (IHasSelection<T>)CurrentComponent;
                component.AddItem(pOption);
                _AddSelection(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasOption.", exception.Message);
            }
        }

        public IJavaAction Check(Component pComponent)
        {
            CurrentComponent = pComponent;
            _Check();
            return this;
        }

        public IJavaAction Check()
        { 
            _Check();
            return this;
        }

        public IJavaAction Uncheck(Component pComponent)
        {
            CurrentComponent = pComponent;
            _Uncheck();
            return this;
        }

        public IJavaAction Uncheck()
        {
            _Uncheck();
            return this;
        }

        // See ISeleniumOverrides
        /*public IJavaAction ChooseOkOnNextConfirmation()
        {
            
            _ChooseOkOnNextConfirmation();
            return this;
        }*/

        // See ISeleniumOverrides
        /*public IJavaAction ChooseCancelOnNextConfirmation()
        {
            
            _ChooseCancelOnNextConfirmation();
            return this;
        }*/

        public IJavaAction Focus(Component pComponent)
        {
            CurrentComponent = pComponent;
            _Focus();
            return this;
        }

        public IJavaAction Focus()
        {
            _Focus();
            return this;
        }

        public IJavaAction Click(Component pComponent)
        {
            CurrentComponent = pComponent;
            _Click();
            return this;
        }

        public IJavaAction Click()
        {
            _Click();
            return this;
        }

        public IJavaAction ClickAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _ClickAt(pComponent);
            return this;
        }

        public IJavaAction ClickAt<T>(Coordinates pCoordinates) where T : Component
        {
            try
            {
                IHasCoordinates<T> component = (IHasCoordinates<T>) CurrentComponent;
                component.Coordinates(pCoordinates);
                _ClickAt(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasCoordinates.", exception.Message);
            }
        }

        public IJavaAction DoubleClick(Component pComponent)
        {
            CurrentComponent = pComponent;
            _DoubleClick();
            return this;
        }

        public IJavaAction DoubleClick()
        {
            _DoubleClick();
            return this;
        }

        public IJavaAction DoubleClickAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _DoubleClickAt(pComponent);
            return this;
        }

        public IJavaAction DoubleClickAt<T>(Coordinates pCoordinates) where T : Component
        {
            try
            {
                IHasCoordinates<T> component = (IHasCoordinates<T>) CurrentComponent;
                component.Coordinates(pCoordinates);
                _DoubleClickAt(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasCoordinates.", exception.Message);
            }
        }

        // See ISeleniumOverrides
        /*public string GetSelectedID(Component pComponent)
        {
            CurrentComponent = pComponent;
            return _GetSelectedID();
        }

        public string GetSelectedID()
        {
            return _GetSelectedID();
        }*/

        public int GetSelectedIndex(IHasSelectionValue pComponent)
        {
            CurrentComponent = _Validate(pComponent);
            return _GetSelectedIndex(pComponent);
        }

        public int GetSelectedIndex()
        {
            try
            {
                IHasSelectionValue component = (IHasSelectionValue)CurrentComponent;
                return _GetSelectedIndex(component);
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasSelectionValue.", exception.Message);
            }
        }

        public List<int> GetSelectedIndices(IHasMultipleSelectionValues pComponent)
        {
            CurrentComponent = _Validate(pComponent);
            return _GetSelectedIndices(pComponent);
        }

        public List<int> GetSelectedIndices()
        {
            try
            {
                IHasMultipleSelectionValues component = (IHasMultipleSelectionValues)CurrentComponent;
                return _GetSelectedIndices(component);
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasMultipleSelectionValues.", exception.Message);
            }
        }

        // See ISeleniumOverrides
        /*public string GetSelectedLabel(Component pComponent)
        {
            CurrentComponent = pComponent;
            return _GetSelectedLabel();
        }

        public string GetSelectedLabel()
        {
            return _GetSelectedLabel();
        }*/

        public string GetSelectedValue(IHasSelectionValue pComponent)
        {
            CurrentComponent = _Validate(pComponent);
            return _GetSelectedValue(pComponent);
        }

        public string GetSelectedValue()
        {
            try
            {
                IHasSelectionValue component = (IHasSelectionValue)CurrentComponent;
                return _GetSelectedValue(component);
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasSelectionValue.", exception.Message);
            }
        }

        public string GetValue(IHasSelectionValue pComponent)
        {
            CurrentComponent = _Validate(pComponent);
            return _GetValue(pComponent);
        }

        public string GetValue()
        {
            try
            {
                IHasSelectionValue component = (IHasSelectionValue)CurrentComponent;
                return _GetValue(component);
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasSelectionValue.", exception.Message);
            }
        }

        public List<string> GetSelectedValues(IHasMultipleSelectionValues pComponent)
        {
            CurrentComponent = _Validate(pComponent);
            return _GetSelectedValues(pComponent);
        }

        public List<string> GetSelectedValues()
        {
            try
            {
                IHasMultipleSelectionValues component = (IHasMultipleSelectionValues)CurrentComponent;
                return _GetSelectedValues(component);
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasMultipleSelectionValues.", exception.Message);
            }
        }

        public List<string> GetValues(IHasMultipleSelectionValues pComponent)
        {
            CurrentComponent = _Validate(pComponent);
            return _GetValues(pComponent);
        }

        public List<string> GetValues()
        {
            try
            {
                IHasMultipleSelectionValues component = (IHasMultipleSelectionValues)CurrentComponent;
                return _GetValues(component);
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasMultipleSelectionValues.", exception.Message);
            }
        }

        public string GetText(Component pComponent)
        {
            CurrentComponent = pComponent;
            return _GetText();
        }

        public string GetText()
        {
            return _GetText();
        }

        public IJavaAction KeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _KeyDown(pComponent);
            return this;
        }

        public IJavaAction KeyDown<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>) CurrentComponent;
                component.Value(pValue);
                _KeyDown(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        public IJavaAction KeyPress<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _KeyPress(pComponent);
            return this;
        }

        public IJavaAction KeyPress<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>)CurrentComponent;
                component.Value(pValue);
                _KeyPress(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        public IJavaAction KeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _KeyUp(pComponent);
            return this;
        }

        public IJavaAction KeyUp<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>)CurrentComponent;
                component.Value(pValue);
                _KeyUp(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        public IJavaAction MouseDown(Component pComponent)
        {
            CurrentComponent = pComponent;
            _MouseDown();
            return this;
        }

        public IJavaAction MouseDown()
        {
            _MouseDown();
            return this;
        }

        public IJavaAction MouseDownAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _MouseDownAt(pComponent);
            return this;
        }

        public IJavaAction MouseDownAt<T>(Coordinates pCoordinates) where T : Component
        {
            try
            {
                IHasCoordinates<T> component = (IHasCoordinates<T>)CurrentComponent;
                component.Coordinates(pCoordinates);
                _MouseDownAt(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasCoordinates.", exception.Message);
            }
        }

        public IJavaAction MouseMove(Component pComponent)
        {
            CurrentComponent = pComponent;
            _MouseMove();
            return this;
        }

        public IJavaAction MouseMove()
        {
            _MouseMove();
            return this;
        }

        public IJavaAction MouseMoveAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _MouseMoveAt(pComponent);
            return this;
        }

        public IJavaAction MouseMoveAt<T>(Coordinates pCoordinates) where T : Component
        {
            try
            {
                IHasCoordinates<T> component = (IHasCoordinates<T>)CurrentComponent;
                component.Coordinates(pCoordinates);
                _MouseMoveAt(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasCoordinates.", exception.Message);
            }
        }

        public IJavaAction MouseOut(Component pComponent)
        {
            CurrentComponent = pComponent;
            _MouseOut();
            return this;
        }

        public IJavaAction MouseOut()
        {
            _MouseOut();
            return this;
        }

        public IJavaAction MouseOver(Component pComponent)
        {
            CurrentComponent = pComponent;
            _MouseOver();
            return this;
        }

        public IJavaAction MouseOver()
        {
            _MouseOver();
            return this;
        }

        public IJavaAction MouseUp(Component pComponent)
        {
            CurrentComponent = pComponent;
            _MouseUp();
            return this;
        }

        public IJavaAction MouseUp()
        {
            _MouseUp();
            return this;
        }

        public IJavaAction MouseUpAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _MouseUpAt(pComponent);
            return this;
        }

        public IJavaAction MouseUpAt<T>(Coordinates pCoordinates) where T : Component
        {
            try
            {
                IHasCoordinates<T> component = (IHasCoordinates<T>)CurrentComponent;
                component.Coordinates(pCoordinates);
                _MouseUpAt(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasCoordinates.", exception.Message);
            }
        }

        public IJavaAction Select<T>(IHasSelection<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _Select(pComponent);
            return this;
        }

        public IJavaAction Select<T>(object pOption) where T : Component
        {
            try
            {
                IHasSelection<T> component = (IHasSelection<T>) CurrentComponent;
                component.AddItem(pOption);
                _Select(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasOption.", exception.Message);
            }
        }

        public IJavaAction SelectFrame(Component pComponent)
        {
            CurrentComponent = pComponent;
            _SelectFrame();
            return this;
        }

        public IJavaAction SelectFrame()
        {
            _SelectFrame();
            return this;
        }

        public IJavaAction SelectWindow(Component pComponent)
        {
            CurrentComponent = pComponent;
            _SelectWindow();
            return this;
        }

        public IJavaAction SelectWindow()
        {
            _SelectWindow();
            return this;
        }

        public IJavaAction SelectPopUp(Component pComponent)
        {
            CurrentComponent = pComponent;
            _SelectPopUp();
            return this;
        }

        public IJavaAction SelectPopUp()
        {
            _SelectPopUp();
            return this;
        }

        public bool IsEnabled(Component pComponent)
        {
            CurrentComponent = pComponent;
            return _IsEnabled();
        }

        public bool IsVisible(Component pComponent)
        {
            CurrentComponent = pComponent;
            return _IsVisible();
        }

        public IJavaAction RightClick(Component pComponent)
        {
            CurrentComponent = pComponent;
            _RightClick();
            return this;
        }

        public IJavaAction RightClick()
        {
            _RightClick();
            return this;
        }

        public IJavaAction ShiftKeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _ShiftKeyDown(pComponent);
            return this;
        }

        public IJavaAction ShiftKeyDown<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>)CurrentComponent;
                component.Value(pValue);
                _ShiftKeyDown(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasOption.", exception.Message);
            }
        }

        public IJavaAction ShiftKeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _ShiftKeyUp(pComponent);
            return this;
        }

        public IJavaAction ShiftKeyUp<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>)CurrentComponent;
                component.Value(pValue);
                _ShiftKeyUp(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasOption.", exception.Message);
            }
        }

        public IJavaAction Type<T>(IHasInputValue<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _Type(pComponent);
            return this;
        }

        public IJavaAction Type<T>(string pValue) where T : Component
        {
            try
            {
                IHasInputValue<T> component = (IHasInputValue<T>) CurrentComponent;
                component.Value(pValue);
                _Type(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasInputValue.", exception.Message);
            }
        }

        // See ISeleniumOverrides
        /*public IJavaAction WaitForPopUp<T>(IHasTimeOut<T> pComponent) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            
            _WaitForPopUp(pComponent);
            return this;
        }*/

        // See ISeleniumOverrides
        /*public IJavaAction WaitForPopUp<T>(string pTimeOut) where T : Component
        {
            try
            {
                
                IHasTimeOut<T> component = (IHasTimeOut<T>) CurrentComponent;
                component.TimeOut(pTimeOut);
                _WaitForPopUp(component);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasTimeOut.", exception.Message);
            }
        }*/

        // See ISeleniumOverrides
        /*public IJavaAction WaitForPopUp()
        {
            _WaitForPopUp();
            return this;
        }*/

        public IJavaAction WaitForNotBusy<T>(IHasTimeOut<T> pComponent) where T : Component
        {
            return WaitForNotBusy(pComponent, WaitDelegate);
        }

        public IJavaAction WaitForNotBusy<T>(IHasTimeOut<T> pComponent, WaitForNotBusyDelegate pDelegate) where T : Component
        {
            CurrentComponent = _Validate(pComponent);
            _WaitForNotBusy(pComponent, pDelegate);
            return this;
        }

        public IJavaAction WaitForNotBusy<T>(string pTimeOut) where T : Component
        {
            // WARNING: strangely, this one cannot simply be reduced to
            //return WaitForNotBusy(pTimeOut, _delegate);
            // type arguments cannot be inferred from usage.

            try
            {
                IHasTimeOut<T> component = (IHasTimeOut<T>)CurrentComponent;
                component.TimeOut(pTimeOut);
                _WaitForNotBusy(component, WaitDelegate);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasTimeOut.", exception.Message);
            }
        }

        public IJavaAction WaitForNotBusy<T>(string pTimeOut, WaitForNotBusyDelegate pDelegate) where T : Component
        {
            try
            {
                IHasTimeOut<T> component = (IHasTimeOut<T>) CurrentComponent;
                component.TimeOut(pTimeOut);
                _WaitForNotBusy(component, pDelegate);
                return this;
            }
            catch (InvalidCastException exception)
            {
                throw new ArgumentException("{0}:Could not cast to IHasTimeOut.", exception.Message);
            }
        }

        public IJavaAction WaitForNotBusy()
        {
            return WaitForNotBusy(WaitDelegate);
        }

        public IJavaAction WaitForNotBusy(WaitForNotBusyDelegate pDelegate)
        {
            _WaitForNotBusy(pDelegate);
            return this;
        }

        public IJavaAction ScrollBrowserWindowToTop()
        {
            _ScrollBrowserWindowToTop();
            return this;
        }

        public IJavaAction ScrollBrowserWindowToBottom()
        {
            _ScrollBrowserWindowToBottom();
            return this;
        }

        public IJavaAction ScrollBrowserWindowTo(int pHorizontalPosition, int pVerticalPosition)
        {
            _ScrollBrowserWindowTo(pHorizontalPosition, pVerticalPosition);
            return this;
        }

        #endregion

        #region Implementation of IRootComponentAware

        public IRootComponent[] RootComponents
        {
            get
            {
                if (_rootComponents == null)
                    _rootComponents = new IRootComponent[]{};
                return _rootComponents;
            }
            set { _rootComponents = value; }
        }

        public List<IRootComponent> GetRootComponents()
        {
            //3.0/3.5 language feature.
            //return RootComponents.Select(i => i).ToList();

            List<IRootComponent> list = new List<IRootComponent>();
            foreach (IRootComponent component in RootComponents)
            {
                list.Add(component);
            }
            return list;
        }

        public IJavaAction SetDefaultRootComponent()
        {
            RootComponents = new IRootComponent[] {};
            return this;
        }

        public IJavaAction SetRootComponent(params IRootComponent[] pRootComponents)
        {
            RootComponents = pRootComponents;
            return this;
        }

        public string GetRootComponentPrefix()
        {
            StringBuilder prefix = new StringBuilder();
            foreach (IRootComponent root in RootComponents)
                prefix.Append(((Component) root).GetBaseComponentString() + ".");

            return (RootComponents.Length > 0)
                       ? prefix.ToString().Substring(0, prefix.Length - 1) // truncate trailing period
                       : String.Empty;
        }

        #endregion

        #region Private

        #region Validations

        private Component _Validate<T>(IHasSelection<T> pComponent) where T : Component
        {
            return (pComponent is Component) ? (Component)pComponent : null;
        }

        private Component _Validate<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            return (pComponent is Component) ? (Component)pComponent : null;
        }

        private Component _Validate<T>(IHasInputValue<T> pComponent) where T : Component
        {
            return (pComponent is Component) ? (Component)pComponent : null;
        }

        private Component _Validate<T>(IHasTimeOut<T> pComponent) where T : Component
        {
            return (pComponent is Component) ? (Component)pComponent : null;
        }

        private Component _Validate(IHasSelectionValue pComponent)
        {
            return (pComponent is Component) ? (Component) pComponent : null;
        }

        private Component _Validate(IHasMultipleSelectionValues pComponent)
        {
            return (pComponent is Component) ? (Component) pComponent : null; 
        }

        #endregion

        #region Private Methods

        private string _GetQueryBase()
        {
            return "getTestFixture()";
        }

        private char _GetCharacter<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (pComponent.Value().Equals(String.Empty))
                throw new ArgumentException("Must specify a character for a key press by setting the Value.");
            if (pComponent.Value().Length > 1)
                throw new ArgumentException("Cannot specify more than one character for a key press.");

            return pComponent.Value()[0];
        }

        private bool _IsCurrentComponentSet()
        {
            if (CurrentComponent == null)
                throw new ApplicationException("Ensure that a Component has been set.");

            return true;
        }

        #endregion

        #region Implementations

        private void _ScrollBrowserWindowToTop()
        {
            // position specified in pixels from the top left corner of the page, horizontally and vertically

            _result = _ScrollBrowserWindowTo(0, 0);
        }

        private void _ScrollBrowserWindowToBottom()
        {
            // position specified in pixels from the top left corner of the page, horizontally and vertically
            // HACK: we do this in a single GetEval call since the height MUST be calculated in the browser.

            // TODO: should probably make this browser aware in the event that the window.scroll is not consistently
            //       supported by all browsers.

            string script = @"
var height = (window.document.innerHeight !== undefined) 
                ? window.document.innerHeight 
                : window.document.body.clientHeight;
window.scroll(0,height);
";
            _result = Selenium.GetEval(script);
        }

        private string _ScrollBrowserWindowTo(int pHorizontalPosition, int pVerticalPosition)
        {
            // position specified in pixels from the top left corner of the page, horizontally and vertically

            // TODO: should probably make this browser aware in the event that the window.scroll is not consistently
            //       supported by all browsers.

            string script = @"window.scroll(" + pHorizontalPosition + "," + pVerticalPosition + ");";
            _result = Selenium.GetEval(script);

            return _result;
        }

        private void _AltKeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.ALT_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _AltKeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.ALT_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _ControlKeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.CTRL_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _ControlKeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.CTRL_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _AddSelection<T>(IHasSelection<T> pComponent) where T : Component
        {
            _Select(pComponent);
        }

        private void _Check()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".check");
        }

        private void _Uncheck()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".uncheck");
        }

        private void _Focus()
        {
            // Does not require the CurrentComponent to be set. By default, this will
            // invoke the focus method on the fixture
            if (CurrentComponent != null)
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".focus");
            else
                _result = Selenium.InvokeMethod(_GetQueryBase() + ".focus");
        }

        private void _Click()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".click");
        }

        private void _ClickAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            // TODO: By default, this method only uses Mouse.Click.LeftButton and does not
            //       allow specification of alternative buttons or the number of clicks.
            //       Ideally, you would be able to specify the Mouse.Click information through
            //       an interface for the component which could then be used here.

            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var point = {0}.createPoint({1}, {2}); 
var mousebutton = {0}.createMouseButton({3})
{4}.robot.click(point, mousebutton, {5});"
                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , pComponent.Coordinates().X
                                              , pComponent.Coordinates().Y
                                              , Mouse.Click.LeftButton().ButtonMask
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix())
                                              , Mouse.Click.LeftButton().Times());

                _result = Selenium.GetEval(script);
            }
        }

        private void _DoubleClick()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".doubleClick");
        }

        private void _DoubleClickAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            // TODO: By default, this method only uses Mouse.Click.LeftButton and does not
            //       allow specification of alternative buttons or the number of clicks.
            //       Ideally, you would be able to specify the Mouse.Click information through
            //       an interface for the component which could then be used here such
            //       as the number of times a button is clicked.

            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var point = {0}.createPoint({1}, {2}); 
var mousebutton = {0}.createMouseButton({3})
{4}.robot.click(point, mousebutton, {5});"
                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , pComponent.Coordinates().X
                                              , pComponent.Coordinates().Y
                                              , Mouse.Click.LeftButton().ButtonMask
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix())
                                              , Mouse.Click.LeftButton().Times(2).Times()); 

                _result = Selenium.GetEval(script);
            }
        }

        private int _GetSelectedIndex(IHasSelectionValue pComponent)
        {
            int index = INVALID_INDEX; // Return an invalid index by default

            if (_IsCurrentComponentSet())
            {
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) 
                    + pComponent.GetSelectedIndexQuery());

                if (!string.IsNullOrEmpty(_result))
                    index = Convert.ToInt32(_result);
            }
            
            return index;
        }

        private List<int> _GetSelectedIndices(IHasMultipleSelectionValues pComponent)
        {
            List<int> indices = new List<int>();
            if (_IsCurrentComponentSet())
            {
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) 
                    + pComponent.GetSelectedIndicesQuery());

                string[] indexStrings = _result.Split(',');

                foreach (string index in indexStrings)
                    indices.Add(Convert.ToInt32(index));
            }
            return indices;
        }

        private string _GetSelectedValue(IHasSelectionValue pComponent)
        {
            return _GetValue(pComponent);
        }

        private List<string> _GetSelectedValues(IHasMultipleSelectionValues pComponent)
        {
            return _GetValues(pComponent);
        }

        private string _GetText()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".text");
            return _result;
        }

        private string _GetValue(IHasSelectionValue pComponent)
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) 
                    + pComponent.GetSelectedValueQuery());
            return _result;
        }

        private List<string> _GetValues(IHasMultipleSelectionValues pComponent)
        {
            List<string> values = new List<string>();
            if (_IsCurrentComponentSet())
            {
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) 
                    + pComponent.GetSelectedValuesQuery());

                string[] indexStrings = _result.Split(',');

                foreach (string value in indexStrings)
                    values.Add(value.Trim());
            }
            return values;
        }

        private void _KeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.NO_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _KeyPress<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.NO_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _KeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.NO_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _MouseDown()
        {
            // TODO: By default, this method only uses Mouse.Click.LeftButton and does not
            //       allow specification of alternative buttons or the number of clicks.
            //       Ideally, you would be able to specify the Mouse.Click information through
            //       an interface for the component which could then be used here.

            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var mousebutton = {0}.createMouseButton({1})
{2}.robot.pressMouse(mousebutton);"
                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , Mouse.Click.LeftButton().ButtonMask
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix())); 

                _result = Selenium.GetEval(script);
            }
        }

        private void _MouseDownAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            // TODO: By default, this method only uses Mouse.Click.LeftButton and does not
            //       allow specification of alternative buttons or the number of clicks.
            //       Ideally, you would be able to specify the Mouse.Click information through
            //       an interface for the component which could then be used here.

            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var point = {0}.createPoint({1}, {2}); 
var mousebutton = {0}.createMouseButton({3})
{4}.robot.pressMouse(point, mousebutton);"
                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , pComponent.Coordinates().X
                                              , pComponent.Coordinates().Y
                                              , Mouse.Click.LeftButton().ButtonMask
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _MouseMove()
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var component = {0}.component(); 
{0}.robot.moveMouse(component);"
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _MouseMoveAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var point = {0}.createPoint({1}, {2}); 
{3}.robot.moveMouse(point);"
                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , pComponent.Coordinates().X
                                              , pComponent.Coordinates().Y
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _MouseOut()
        {
            throw new NotImplementedException("Not implemented in FEST.");

            /*if (_IsCurrentComponentSet())
            {
                // Needs support in FEST to move the mouse to the outside boundary of a component
                String script = String.Format(@"
var component = {0}.component(); 
{0}.robot.moveMouse(component);"
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }*/
        }

        private void _MouseOver()
        {
            _MouseMove();
        }

        private void _MouseUp()
        {
            // TODO: By default, this method only uses Mouse.Click.LeftButton and does not
            //       allow specification of alternative buttons or the number of clicks.
            //       Ideally, you would be able to specify the Mouse.Click information through
            //       an interface for the component which could then be used here.

            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var mousebutton = {0}.createMouseButton({1})
{2}.robot.releaseMouse(mousebutton);"
                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , Mouse.Click.LeftButton().ButtonMask
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _MouseUpAt<T>(IHasCoordinates<T> pComponent) where T : Component
        {
            // TODO: By default, this method only uses Mouse.Click.LeftButton and does not
            //       allow specification of alternative buttons or the number of clicks.
            //       Ideally, you would be able to specify the Mouse.Click information through
            //       an interface for the component which could then be used here.

            // WARNING: There is no releaseMouse method in FEST which takes a Point as a parameter

            _MouseUp();
        }

        private void _Select<T>(IHasSelection<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                string queryBaseString = Selenium.JSPrefix + _GetQueryBase();
                string componentString = Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix());
                string eval;

                if ( pComponent is IHasRowSelection<T> && ((IHasRowSelection<T>)pComponent).SelectedRows().Count > 0 )
                {
                    IHasRowSelection<T> component = pComponent as IHasRowSelection<T>;
                    eval = (component.SelectedRows().Count > 1) 
                               ? (component is IRequiresJavaObjectCreation )
                                     ? component.SelectedRowsEvalMethod(queryBaseString, componentString)
                                     : component.SelectedRowsEvalMethod(componentString) 
                               : (component is IRequiresJavaObjectCreation ) 
                                     ? component.SelectedRowEvalMethod(queryBaseString, componentString) 
                                     : component.SelectedRowEvalMethod(componentString)
                        ;
                }
                else
                    eval = (pComponent.SelectedItems().Count > 1) 
                               ? (pComponent is IRequiresJavaObjectCreation ) 
                                     ? pComponent.SelectedItemsEvalMethod(queryBaseString, componentString) 
                                     : pComponent.SelectedItemsEvalMethod(componentString)
                               : (pComponent is IRequiresJavaObjectCreation) 
                                     ? pComponent.SelectedItemEvalMethod(queryBaseString, componentString) 
                                     : pComponent.SelectedItemEvalMethod(componentString)
                        ;  
                
                _result = Selenium.GetEval(eval);
            }
        }

        private void _SelectFrame()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(_GetQueryBase() + ".focus");
        }

        private void _SelectWindow()
        {
            _SelectFrame();
        }

        private void _SelectPopUp()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".showPopupMenu");
        }

        private void _RightClick()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".rightClick");
        }

        private bool _IsEnabled()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".component().isEnabled");
            return (_result == "true") ? true : false;
        }

        private bool _IsVisible()
        {
            if (_IsCurrentComponentSet())
                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".component().isVisible");
            return (_result == "true") ? true : false;
        }

        private void _ShiftKeyDown<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.SHIFT_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _ShiftKeyUp<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                String script = String.Format(@"
var javaObject = {0}.createKeyPressInfo({1}, {2}); 
{3}.pressAndReleaseKey(javaObject);"

                                              , Selenium.JSPrefix + _GetQueryBase()
                                              , _provider_EN.GetCodeForChar(_GetCharacter(pComponent))
                                              , _provider_EN.SHIFT_MASK
                                              , Selenium.JSPrefix + CurrentComponent.GetQueryString(GetRootComponentPrefix()));

                _result = Selenium.GetEval(script);
            }
        }

        private void _Type<T>(IHasInputValue<T> pComponent) where T : Component
        {
            if (_IsCurrentComponentSet())
            {
                if (pComponent.Value().Equals(String.Empty))
                    throw new ArgumentException("Must specify a string to Type for the Value.");

                _result = Selenium.InvokeMethod(CurrentComponent.GetQueryString(GetRootComponentPrefix()) + ".enterText", pComponent.Value());
            }
        }

        // See ISeleniumOverrides
        /*private void _WaitForPopUp<T>(IHasTimeOut<T> pComponent) where T : Component
        {
            // NOTE: Create JavaScript helper method on the hosted page which matches the method invoked
            if (_IsCurrentComponentSet())
                _result = Selenium.GetEval(Selenium.JSPrefix + ".waitForPopUp(" + pComponent.TimeOut() + ");");
        }*/

        // See ISeleniumOverrides
        /*private void _WaitForPopUp()
        {
            // NOTE: Create JavaScript helper method on the hosted page which matches the method invoked
            //       Does not require the current component to be set
            _result = Selenium.GetEval(Selenium.JSPrefix + ".waitForPopUp();");
        }*/

        private void _WaitForNotBusy<T>(IHasTimeOut<T> pComponent, WaitForNotBusyDelegate pDelegate) where T : Component
        {
            // NOTE: Create JavaScript helper method on the hosted page which matches the method invoked
            
            if (_IsCurrentComponentSet())
            {
                _result = pDelegate();

                //_result = Selenium.GetEval("window.waitForNotBusy(" + pComponent.TimeOut() + ");");
            }
        }

        private void _WaitForNotBusy(WaitForNotBusyDelegate pDelegate)
        {
            // NOTE: Create JavaScript helper method on the hosted page which matches the method invoked
            //       Does not require the current component to be set

            _result = pDelegate();
            
            //_result = Selenium.GetEval("window.waitForNotBusy();");
        }

        #endregion

        #endregion

    }
}
