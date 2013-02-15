using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Collections.Generic;

namespace SampleRIASilverlightDebug.lv.lu.cds.businessobjects.constants
{
    public class IssueConstants
    {
        public const int INIT_VALUE = -1;
		
		public const String PRIORITY_HIGH  = "Augsts"; 
		public const String PRIORITY_MEDIUM = "Vidējs"; 
		public const String PRIORITY_LOW = "Zems";
		
		public const String RISK_HIGH = "Augsts"; 
		public const String RISK_MEDIUM = "Vidējs"; 
		public const String RISK_LOW = "Zems";
		
		public const String STATUS_1 = "Atvērts"; 
		public const String STATUS_2 = "Piesaistīts izstrādātājam"; 
		public const String STATUS_3 = "Tiek testēt"; 
		public const String STATUS_4 = "Aizvērts"; 
		public const String STATUS_5 = "Neveiksmīgi notestēts";
    }
    public class Priorities : List<String>
    {
        public Priorities()
        {
            this.Add(IssueConstants.PRIORITY_HIGH);
            this.Add(IssueConstants.PRIORITY_MEDIUM);
            this.Add(IssueConstants.PRIORITY_LOW);
        }
    }
    public class Risks : List<String>
    {
        public Risks()
        {
            this.Add(IssueConstants.RISK_HIGH);
            this.Add(IssueConstants.RISK_MEDIUM);
            this.Add(IssueConstants.RISK_LOW);
        }
    }
}
