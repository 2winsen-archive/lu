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
using SampleRIASilverlightDebug.lv.lu.cds.businessobjects;

namespace SampleRIASilverlightDebug.lv.lu.cds.model.service
{
    public interface ISampleRIAService
    {
        User Login(String username, String passwordHash);
		void Register(User user);
		void AddIssue(Issue issue);
    }
}
