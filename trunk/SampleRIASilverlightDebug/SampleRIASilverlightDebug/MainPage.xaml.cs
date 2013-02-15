using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using SampleRIASilverlightDebug.lv.lu.cds.components;
using SampleRIASilverlightDebug.lv.lu.cds.model;

namespace SampleRIASilverlightDebug
{
    public partial class MainPage : UserControl
    {
        private DomainModel domain = DomainModel.getInstance();

        public MainPage()
        {
            InitializeComponent();
            LayoutRoot.DataContext = domain;
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Login login = new Login();
            login.Show();
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            if (DomainModel.getInstance().IsLoggedIn)
            {
                NewIssue newIssue = new NewIssue();
                newIssue.goToEntryMode(true);
                newIssue.Show();
            }
        }

    }
}
