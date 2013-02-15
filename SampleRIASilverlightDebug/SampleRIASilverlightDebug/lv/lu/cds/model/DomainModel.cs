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
using SampleRIASilverlightDebug.lv.lu.cds.model.service;
using SampleRIASilverlightDebug.lv.lu.cds.businessobjects;
using SampleRIASilverlightDebug.lv.lu.cds.businessobjects.constants;
using System.Collections.Generic;
using System.ComponentModel;
using System.Collections.ObjectModel;
using System.Windows.Data;

namespace SampleRIASilverlightDebug.lv.lu.cds.model
{
    public class DomainModel : INotifyPropertyChanged
    {
        private static DomainModel instance;

        private DomainModel()
        {
        }

        public event PropertyChangedEventHandler PropertyChanged;
        public void OnPropertyChanged(PropertyChangedEventArgs e)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, e);
        }

        public static DomainModel getInstance()
        {
            if ( instance == null )
            {
                instance = new DomainModel();
            }
            return instance;
        }

        public ISampleRIAService service;
        public User _user;
        public User User
        {
            get 
            { 
                return _user; 
            }
            set 
            { 
                _user = value;
                OnPropertyChanged(new PropertyChangedEventArgs("User"));
            }
        }

        public bool IsLoggedIn = false;

        public ObservableCollection<Issue> _Issues = new ObservableCollection<Issue>();
        public ObservableCollection<Issue> Issues
        {
            get
            {
                return _Issues;
            }
            set
            {
                _Issues = value;
            }
        }

        public String _IssuesSize;
        public String IssuesSize
        {
            get
            {
                return (IsLoggedIn) ? "Kopā: " + Issues.Count + " rezultāti" : "";
            }
            set
            {
                _IssuesSize = value;
                OnPropertyChanged(new PropertyChangedEventArgs("IssuesSize"));
            }
        }
		
		public int _LastId = 1;
        public int LastId
        {
            get
            {
                return _LastId;
            }
            set
            {
                _LastId = value;
                OnPropertyChanged(new PropertyChangedEventArgs("LastId"));
            }
        }

        public User login(String username, String passwordHash)
		{
			return this.service.Login(username, passwordHash);
		}
		
		public void register(User user)
		{
			this.service.Register(user);
		}
		
		public void addIssue(Issue issue)
		{
			this.service.AddIssue(issue);
		}

        public void storeTestIsses()
        {
            Issue data = new Issue();
            data.id = LastId++;
            data.author = "Vitālijs";
            data.created = new DateTime();
            data.title = "Search not working";
            data.description = "Search is not working properly";
            data.functionality = "Search";
            data.notes = "";
            data.priority = IssueConstants.PRIORITY_LOW;
            data.responder = "Ainārs";
            data.risk = IssueConstants.RISK_LOW;
            data.status = IssueConstants.STATUS_2;
            data.system = "Persy";
            Issues.Add(data);

            Issue data2 = new Issue();
            data2.id = LastId++;
            data2.author = "Vitālijs";
            data2.created = new DateTime();
            data2.title = "Profit not working";
            data2.description = "Profit is not working properly";
            data2.functionality = "Profit";
            data2.notes = "";
            data2.priority = IssueConstants.PRIORITY_HIGH;
            data2.responder = "Ainārs";
            data2.risk = IssueConstants.RISK_HIGH;
            data2.status = IssueConstants.STATUS_1;
            data2.system = "Persy Server";
            Issues.Add(data2);

            Issue data3 = new Issue();
            data3.id = LastId++;
            data3.author = "Pēteris";
            data3.created = new DateTime();
            data3.title = "Performance issues";
            data3.description = "Performance issues";
            data3.functionality = "General";
            data3.notes = "";
            data3.priority = IssueConstants.PRIORITY_LOW;
            data3.responder = "Ainārs";
            data3.risk = IssueConstants.RISK_LOW;
            data3.status = IssueConstants.STATUS_4;
            data3.system = "Persy";
            Issues.Add(data3);

            Issue data4 = new Issue();
            data4.id = LastId++;
            data4.author = "Vitālijs";
            data4.created = new DateTime();
            data4.title = "Button color is incorrect";
            data4.description = "Button color should be slate";
            data4.functionality = "General";
            data4.notes = "";
            data4.priority = IssueConstants.PRIORITY_LOW;
            data4.responder = "Ainārs";
            data4.risk = IssueConstants.RISK_LOW;
            data4.status = IssueConstants.STATUS_4;
            data4.system = "Persy";
            Issues.Add(data4);
        }
    }

}
