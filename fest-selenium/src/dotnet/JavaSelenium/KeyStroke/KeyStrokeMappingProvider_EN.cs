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

using System;
using System.Collections.Generic;

namespace JavaSelenium.KeyStroke
{
    public class KeyStrokeMappingProvider_EN : KeyStrokeMappingProvider, IKeyStrokeMappingProvider
    {
        #region Private Members

        private List<KeyStrokeMapping> _mappings;
        private Dictionary<char, int> _charToCode;
        private Dictionary<char, int> _charToModifier;
        private Dictionary<KeyStroke, char> _keyStrokeToChar;
        private Dictionary<char, KeyStrokeMapping> _charToMapping;
        private Dictionary<KeyStroke, KeyStrokeMapping> _keyStrokeToMapping;

        #endregion

        #region Public Methods

        public KeyStrokeMappingProvider_EN()
        {
            GetKeyStrokeMappings();
        }

        /// <summary>
        /// Gets an ASCII code value for a given character
        /// </summary>
        /// <param name="pCharacter">The character to convert.</param>
        /// <returns>int The ASCII code value as an integer</returns>
        public int GetCodeForChar(char pCharacter)
        {
            int code;
            if (_charToCode.TryGetValue(pCharacter, out code))
                return code;

            throw new ApplicationException(String.Format("{0} : does not have a valid ASCII code", pCharacter));
        }

        /// <summary>
        /// Gets an integer represenation of the Modifier bit mask
        /// </summary>
        /// <param name="pCharacter">The character to convert.</param>
        /// <returns>int The integer representation of the Modifier bit mask</returns>
        public int GetModifierForChar(char pCharacter)
        {
            int modifier;
            if (_charToModifier.TryGetValue(pCharacter, out modifier))
                return modifier;

            throw new ApplicationException(String.Format("{0} : does not have a valid modifier code", pCharacter));
        }

        /// <summary>
        /// Returns a char for a given ASCII code
        /// </summary>
        /// <param name="pKeyStroke">The KeyStroke object to convert</param>
        /// <returns>char The character representation of the ASCII code</returns>
        public char GetCharForKeyStroke(KeyStroke pKeyStroke)
        {
            char character;
            if (_keyStrokeToChar.TryGetValue(pKeyStroke, out character))
                return character;

            throw new ApplicationException(String.Format("{0}/{1} : does not have an associated char", pKeyStroke.KeyCode, pKeyStroke.Modifier));
        }

        /// <summary>
        /// Returns a KeyStrokeMapping object for a given character
        /// </summary>
        /// <param name="pCharacter">The character to convert.</param>
        /// <returns>KeyStrokeMapping The KeyStrokeMapping object associated with the character.</returns>
        public KeyStrokeMapping GetMappingForChar(char pCharacter)
        {
            KeyStrokeMapping map;
            if (_charToMapping.TryGetValue(pCharacter, out map))
                return map;

            throw new ApplicationException(String.Format("{0} : does not have an associated KeyStrokeMapping object.", pCharacter));
        }

        /// <summary>
        /// Returns a KeyStrokeMapping object for a given ASCII code
        /// </summary>
        /// <param name="pKeyStroke">The KeyStroke object.</param>
        /// <returns>KeyStrokeMapping The KeyStrokeMapping object associated with the ASCII code.</returns>
        public KeyStrokeMapping GetMappingForKeyStroke(KeyStroke pKeyStroke)
        {
            KeyStrokeMapping map;
            if (_keyStrokeToMapping.TryGetValue(pKeyStroke, out map))
                return map;

            throw new ApplicationException(String.Format("{0}/{1} : does not have an associted KeyStrokeMapping object.", pKeyStroke.KeyCode, pKeyStroke.Modifier));
        }

        /// <summary>
        /// Returns a List of KeyStrokeMappings for this provider.
        /// </summary>
        /// <returns>List of KeyStrokeMappings.</returns>
        public List<KeyStrokeMapping> GetKeyStrokeMappings()
        {
            if (_mappings == null)
            {
                _mappings = new List<KeyStrokeMapping>();

                _mappings.Add(new KeyStrokeMapping('0', new KeyStroke(KeyConstants.VK_0, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping(')', new KeyStroke(KeyConstants.VK_0, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('1', new KeyStroke(KeyConstants.VK_1, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('!', new KeyStroke(KeyConstants.VK_1, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('2', new KeyStroke(KeyConstants.VK_2, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('@', new KeyStroke(KeyConstants.VK_2, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('3', new KeyStroke(KeyConstants.VK_3, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('#', new KeyStroke(KeyConstants.VK_3, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('4', new KeyStroke(KeyConstants.VK_4, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('$', new KeyStroke(KeyConstants.VK_4, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('5', new KeyStroke(KeyConstants.VK_5, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('%', new KeyStroke(KeyConstants.VK_5, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('6', new KeyStroke(KeyConstants.VK_6, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('^', new KeyStroke(KeyConstants.VK_6, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('7', new KeyStroke(KeyConstants.VK_7, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('&', new KeyStroke(KeyConstants.VK_7, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('8', new KeyStroke(KeyConstants.VK_8, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('*', new KeyStroke(KeyConstants.VK_8, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('9', new KeyStroke(KeyConstants.VK_9, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('(', new KeyStroke(KeyConstants.VK_9, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('a', new KeyStroke(KeyConstants.VK_A, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('A', new KeyStroke(KeyConstants.VK_A, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('b', new KeyStroke(KeyConstants.VK_B, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('B', new KeyStroke(KeyConstants.VK_B, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('`', new KeyStroke(KeyConstants.VK_BACK_QUOTE, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('~', new KeyStroke(KeyConstants.VK_BACK_QUOTE, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('\\', new KeyStroke(KeyConstants.VK_BACK_SLASH, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('|', new KeyStroke(KeyConstants.VK_BACK_SLASH, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('\b', new KeyStroke(KeyConstants.VK_BACK_SPACE, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('c', new KeyStroke(KeyConstants.VK_C, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('C', new KeyStroke(KeyConstants.VK_C, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping(']', new KeyStroke(KeyConstants.VK_CLOSE_BRACKET, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('}', new KeyStroke(KeyConstants.VK_CLOSE_BRACKET, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping(',', new KeyStroke(KeyConstants.VK_COMMA, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('<', new KeyStroke(KeyConstants.VK_COMMA, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('d', new KeyStroke(KeyConstants.VK_D, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('D', new KeyStroke(KeyConstants.VK_D, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('', new KeyStroke(KeyConstants.VK_DELETE, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('e', new KeyStroke(KeyConstants.VK_E, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('E', new KeyStroke(KeyConstants.VK_E, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('\n', new KeyStroke(KeyConstants.VK_ENTER, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('\r', new KeyStroke(KeyConstants.VK_ENTER, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('=', new KeyStroke(KeyConstants.VK_EQUALS, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('+', new KeyStroke(KeyConstants.VK_EQUALS, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('', new KeyStroke(KeyConstants.VK_ESCAPE, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('f', new KeyStroke(KeyConstants.VK_F, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('F', new KeyStroke(KeyConstants.VK_F, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('g', new KeyStroke(KeyConstants.VK_G, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('G', new KeyStroke(KeyConstants.VK_G, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('h', new KeyStroke(KeyConstants.VK_H, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('H', new KeyStroke(KeyConstants.VK_H, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('i', new KeyStroke(KeyConstants.VK_I, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('I', new KeyStroke(KeyConstants.VK_I, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('j', new KeyStroke(KeyConstants.VK_J, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('J', new KeyStroke(KeyConstants.VK_J, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('k', new KeyStroke(KeyConstants.VK_K, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('K', new KeyStroke(KeyConstants.VK_K, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('l', new KeyStroke(KeyConstants.VK_L, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('L', new KeyStroke(KeyConstants.VK_L, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('m', new KeyStroke(KeyConstants.VK_M, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('M', new KeyStroke(KeyConstants.VK_M, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('-', new KeyStroke(KeyConstants.VK_MINUS, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('_', new KeyStroke(KeyConstants.VK_MINUS, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('n', new KeyStroke(KeyConstants.VK_N, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('N', new KeyStroke(KeyConstants.VK_N, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('o', new KeyStroke(KeyConstants.VK_O, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('O', new KeyStroke(KeyConstants.VK_O, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('[', new KeyStroke(KeyConstants.VK_OPEN_BRACKET, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('{', new KeyStroke(KeyConstants.VK_OPEN_BRACKET, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('p', new KeyStroke(KeyConstants.VK_P, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('P', new KeyStroke(KeyConstants.VK_P, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('.', new KeyStroke(KeyConstants.VK_PERIOD, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('>', new KeyStroke(KeyConstants.VK_PERIOD, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('q', new KeyStroke(KeyConstants.VK_Q, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('Q', new KeyStroke(KeyConstants.VK_Q, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('\'', new KeyStroke(KeyConstants.VK_QUOTE, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('"', new KeyStroke(KeyConstants.VK_QUOTE, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('r', new KeyStroke(KeyConstants.VK_R, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('R', new KeyStroke(KeyConstants.VK_R, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('s', new KeyStroke(KeyConstants.VK_S, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('S', new KeyStroke(KeyConstants.VK_S, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping(';', new KeyStroke(KeyConstants.VK_SEMICOLON, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping(':', new KeyStroke(KeyConstants.VK_SEMICOLON, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('/', new KeyStroke(KeyConstants.VK_SLASH, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('?', new KeyStroke(KeyConstants.VK_SLASH, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping(' ', new KeyStroke(KeyConstants.VK_SPACE, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('t', new KeyStroke(KeyConstants.VK_T, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('T', new KeyStroke(KeyConstants.VK_T, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('u', new KeyStroke(KeyConstants.VK_U, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('U', new KeyStroke(KeyConstants.VK_U, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('v', new KeyStroke(KeyConstants.VK_V, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('V', new KeyStroke(KeyConstants.VK_V, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('w', new KeyStroke(KeyConstants.VK_W, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('W', new KeyStroke(KeyConstants.VK_W, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('x', new KeyStroke(KeyConstants.VK_X, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('X', new KeyStroke(KeyConstants.VK_X, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('y', new KeyStroke(KeyConstants.VK_Y, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('Y', new KeyStroke(KeyConstants.VK_Y, SHIFT_MASK)));
                _mappings.Add(new KeyStrokeMapping('z', new KeyStroke(KeyConstants.VK_Z, NO_MASK)));
                _mappings.Add(new KeyStrokeMapping('Z', new KeyStroke(KeyConstants.VK_Z, SHIFT_MASK)));
            };

            _PopulateCharToCodeDictionary();
            _PopulateCharToModifierDictionary();
            _PopulateKeyStrokeToCharDictionary();
            _PopulateCharToMappingDictionary();
            _PopulateKeyStrokeToMappingDictionary();

            return _mappings;
        }

        #endregion

        #region Private Methods

        private void _PopulateCharToCodeDictionary()
        {
            if (_charToCode != null) return;
            _charToCode = new Dictionary<char, int>();
            foreach (KeyStrokeMapping map in _mappings)
                _charToCode.Add(map.Character, map.KeyStroke.KeyCode);
        }

        private void _PopulateCharToModifierDictionary()
        {
            if (_charToModifier != null) return;
            _charToModifier = new Dictionary<char, int>();
            foreach (KeyStrokeMapping map in _mappings)
                _charToModifier.Add(map.Character, map.KeyStroke.Modifier);
        }

        private void _PopulateKeyStrokeToCharDictionary()
        {
            if (_keyStrokeToChar != null) return;
            _keyStrokeToChar = new Dictionary<KeyStroke, char>();
            foreach (KeyStrokeMapping map in _mappings)
                _keyStrokeToChar.Add(map.KeyStroke, map.Character);
        }

        private void _PopulateCharToMappingDictionary()
        {
            if (_charToMapping != null) return;
            _charToMapping = new Dictionary<char, KeyStrokeMapping>();
            foreach (KeyStrokeMapping map in _mappings)
                _charToMapping.Add(map.Character, map);
        }

        private void _PopulateKeyStrokeToMappingDictionary()
        {
            if (_keyStrokeToMapping != null) return;
            _keyStrokeToMapping = new Dictionary<KeyStroke, KeyStrokeMapping>();
            foreach (KeyStrokeMapping map in _mappings)
                _keyStrokeToMapping.Add(map.KeyStroke, map);
        }

        #endregion
    }
}
