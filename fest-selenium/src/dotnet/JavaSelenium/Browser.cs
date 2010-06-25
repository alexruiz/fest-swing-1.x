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

namespace JavaSelenium
{
    public abstract class Browser
    {
        private readonly String _browserString;
        private readonly String _browserConstant;
        private readonly String _agentInfo;
        private readonly String _version;

        protected Browser(String pBrowserString, String pBrowserConstant, String pAgentInfo, String pVersion)
        {
            _browserString = pBrowserString;
            _browserConstant = pBrowserConstant;
            _agentInfo = pAgentInfo;
            _version = pVersion;
        }

        public String String
        {
            get { return _browserString; }
        }

        public String Constant
        {
            get { return _browserConstant; }
        }

        public String AgentInfo
        {
            get { return _agentInfo; }
        }

        public String Version
        {
            get { return _version; }
        }
    }

    public class FireFox : Browser
    {
        public FireFox()
            : base( "*firefox", 
                    "Firefox/3.", 
                    "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.3",
                    "3.5.3")
        {}
    }

    public class InternetExplorer : Browser
    {
        public InternetExplorer() 
            : base( "*iexploreproxy", 
                    "MSIE",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.2; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)",
                    "7.0")
        {}
    }

    public class Safari : Browser
    {
        public Safari() 
            : base( "*safari", 
                    "Safari",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-us) AppleWebKit/531.9 (KHTML, like Gecko) Version/4.0.3 Safari/531.9", 
                    "4.0.3")
        {}
    }

    public class Opera : Browser
    {
        public Opera() 
            : base( "opera",
                    "Opera", 
                    "Opera/9.80 (Windows NT 5.2; U; en) Presto/2.2.15 Version/10.00", 
                    "10.00")
        {}
    }
    
    public static class Browsers
    {
        public static FireFox FireFox = new FireFox();
        public static InternetExplorer IE = new InternetExplorer();
        public static Safari Safari = new Safari();
        public static Opera Opera = new Opera();
    }

}
