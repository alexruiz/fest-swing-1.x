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

    public class KeyStroke
    {
        private readonly int _keyCode;
        private readonly int _modifier;

        /// <summary>
        /// Creates a KeyStroke which consists of an integer represenation of an ASCII code and a 
        /// modifier bit mask for key chords (i.e. CTRL-A)
        /// </summary>
        /// <param name="pKeyCode">The integer represenation of the ASCII code.</param>
        /// <param name="pModifier">The bit mask key modifier.</param>
        public KeyStroke(int pKeyCode, int pModifier)
        {
            _keyCode = pKeyCode;
            _modifier = pModifier;
        }

        /// <summary>
        /// Gets the KeyCode
        /// </summary>
        public int KeyCode
        {
            get { return _keyCode; }
        }

        /// <summary>
        /// Gets the Modifier
        /// </summary>
        public int Modifier
        {
            get { return _modifier; }
        }

    }

    public class KeyStrokeMapping
    {
        private readonly char _character;
        private readonly KeyStroke _keyStroke;

        /// <summary>
        /// Creates a mapping of a key stroke character, it's associated KeyStroke.
        /// Internationalization will require these mappings to be different between languagues. A provider is 
        /// required to map to the keyboard mapping per language.
        /// </summary>
        /// <param name="pCharacter">The character being mapped.</param>
        /// <param name="pKeyStroke">The associated KeyStroke</param>
        public KeyStrokeMapping(char pCharacter, KeyStroke pKeyStroke)
        {
            _character = pCharacter;
            _keyStroke = pKeyStroke;
        }

        /// <summary>
        /// Gets the character associated with the key stroke.
        /// </summary>
        public char Character
        {
            get { return _character; }
        }

        /// <summary>
        /// Gets the KeyStroke
        /// </summary>
        public KeyStroke KeyStroke
        {
            get { return _keyStroke; }
        }
    }
}
