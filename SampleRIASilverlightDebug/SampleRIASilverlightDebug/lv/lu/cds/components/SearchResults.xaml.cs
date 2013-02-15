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
using SampleRIASilverlightDebug.lv.lu.cds.model;
using SampleRIASilverlightDebug.lv.lu.cds.businessobjects;

namespace SampleRIASilverlightDebug.lv.lu.cds.components
{
    public partial class SearchResults : UserControl
    {
        private DomainModel domain = DomainModel.getInstance();

        public SearchResults()
        {
            InitializeComponent();
            dataGrid1.DataContext = domain;
        }

        private void dataGrid1_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (sender is DataGrid && (sender as DataGrid).SelectedItem is Issue) 
            {
                if (DomainModel.getInstance().IsLoggedIn)
                {
                    NewIssue newIssue = new NewIssue();
                    newIssue.viewOnly = true;
                    newIssue.populateData((sender as DataGrid).SelectedItem as Issue);
                    newIssue.Show();
                }
            }
        }
    }
}
