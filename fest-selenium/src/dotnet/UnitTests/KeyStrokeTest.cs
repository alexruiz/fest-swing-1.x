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

using System.Collections.Generic;
using JavaSelenium.KeyStroke;
using NUnit.Framework;

namespace UnitTests
{
    [TestFixture]
    public class KeyStrokeTest
    {
        private KeyStrokeMappingProvider_EN _provider;

        [SetUp]
        public void SetUp()
        {
            _provider = new KeyStrokeMappingProvider_EN();
        }

        [TearDown]
        public void TearDown()
        {}

        [Test]
        public void TestKeyStrokeMapping()
        {
            KeyStrokeMapping map = new KeyStrokeMapping('x', new KeyStroke(1, 2));

            Assert.IsNotNull(map);
            Assert.IsNotNull(map.KeyStroke);

            Assert.AreEqual('x', map.Character);
            Assert.AreEqual(1, map.KeyStroke.KeyCode);
            Assert.AreEqual(2, map.KeyStroke.Modifier);
        }

        [Test]
        public void TestGetCodeForChar()
        {
            int code = _provider.GetCodeForChar('2');

            Assert.IsNotNull(code);
            Assert.AreEqual(KeyConstants.VK_2, code);
        }

        [Test]
        public void TestGetModifierForChar()
        {
            int modifier = _provider.GetModifierForChar('2');

            Assert.IsNotNull(modifier);
            Assert.AreEqual(_provider.NO_MASK, modifier);
        }

        [Test]
        public void TestGetCharForKeyStroke()
        {
            KeyStrokeMapping map = _provider.GetMappingForChar('2');

            char character = _provider.GetCharForKeyStroke(map.KeyStroke);

            Assert.IsNotNull(character);
            Assert.AreEqual('2', character);
        }

        [Test]
        public void TestGetMappingForChar()
        {
            KeyStrokeMapping map = _provider.GetMappingForChar(')');

            Assert.IsNotNull(map);
            Assert.IsNotNull(map.KeyStroke);

            Assert.AreEqual(')', map.Character);
            Assert.AreEqual(KeyConstants.VK_0, map.KeyStroke.KeyCode);
            Assert.AreEqual(_provider.SHIFT_MASK, map.KeyStroke.Modifier);
        }

        [Test]
        public void TestGetMappingForKeyStroke()
        {
            KeyStrokeMapping map = _provider.GetMappingForChar('0');

            Assert.IsNotNull(map);
            Assert.IsNotNull(map.KeyStroke);

            map = _provider.GetMappingForKeyStroke(map.KeyStroke);

            Assert.AreEqual('0', map.Character);
            Assert.AreEqual(KeyConstants.VK_0, map.KeyStroke.KeyCode);
            Assert.AreEqual(_provider.NO_MASK, map.KeyStroke.Modifier);
        }

        [Test]
        public void TestGetKeyStrokeMappings()
        {
            List<KeyStrokeMapping> mappings = _provider.GetKeyStrokeMappings();
            
            Assert.IsNotNull(mappings);
            
            foreach(KeyStrokeMapping map in mappings)
            {
                Assert.IsNotNull(map.Character);
                Assert.IsNotNull(map.KeyStroke.KeyCode);
                Assert.IsNotNull(map.KeyStroke.Modifier);
            }
        }
    }
}
