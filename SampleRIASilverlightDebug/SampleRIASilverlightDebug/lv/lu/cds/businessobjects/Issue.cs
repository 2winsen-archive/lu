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

namespace SampleRIASilverlightDebug.lv.lu.cds.businessobjects
{
    public class Issue
    {
        public int id { get; set; }
        public String status { get; set; }
        public String author { get; set; }
        public DateTime? created { get; set; }
        public String title { get; set; }
        public String description { get; set; }
        public String priority { get; set; }
        public String system { get; set; }
        public String functionality { get; set; }
        public String risk { get; set; }
        public String responder { get; set; }
        public String notes { get; set; }
    }
}
