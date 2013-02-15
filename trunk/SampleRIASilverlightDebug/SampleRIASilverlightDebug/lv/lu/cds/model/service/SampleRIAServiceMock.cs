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
using System.Collections;
using System.Collections.Generic;
using SampleRIASilverlightDebug.lv.lu.cds.businessobjects;
using System.ComponentModel;

namespace SampleRIASilverlightDebug.lv.lu.cds.model.service
{
    public class SampleRIAServiceMock : ISampleRIAService
    {
        private List<User> users = new List<User>();
		
        public User Login(String username, String passwordHash)
        {
            foreach(User user in users)
            {
                if ( String.Equals(user.username, username) && String.Equals(user.passwordHash, passwordHash) )
                {
                    return user;
                }
            }
            return null;
        }
		
        public void Register(User user)
        {
            users.Add( user );
        }
		
        public void AddIssue(Issue issue)
        {
            DomainModel.getInstance().Issues.Add(issue);
            DomainModel.getInstance().LastId++;
            DomainModel.getInstance().OnPropertyChanged(new PropertyChangedEventArgs("IssuesSize"));
        }
    }
}
