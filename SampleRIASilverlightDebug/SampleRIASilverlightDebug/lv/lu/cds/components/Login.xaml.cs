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
using SampleRIASilverlightDebug.lv.lu.cds.businessobjects;
using SampleRIASilverlightDebug.lv.lu.cds.model;
using SampleRIASilverlightDebug.lv.lu.cds.model.service;
using SampleRIASilverlightDebug.lv.lu.cds.utils;
using System.Security.Cryptography;
using System.Text;
using System.ComponentModel;

namespace SampleRIASilverlightDebug.lv.lu.cds.components
{
    public partial class Login : ChildWindow
    {
        private LoginForm form;
        private DomainModel domain;

        public Login()
        {
            InitializeComponent();
            form = new LoginForm();
            LayoutRoot.DataContext = form;
        }

        private void OKButton_Click(object sender, RoutedEventArgs e)
        {
            if (ValidationUtils.notBlank(username.Text) && ValidationUtils.notBlank(password.Password))
            {
                SHA256 sha = new SHA256Managed();
                Byte[] inputBytes = Encoding.UTF8.GetBytes(password.Password);
                Byte[] hashedBytes = sha.ComputeHash(inputBytes);

                User user = null;
                if ((user = DomainModel.getInstance().login(username.Text, BitConverter.ToString(hashedBytes))) != null)
                {
                    DomainModel.getInstance().IsLoggedIn = true;
                    this.DialogResult = true;
                    domain.User = user;
                    domain.storeTestIsses();
                    DomainModel.getInstance().OnPropertyChanged(new PropertyChangedEventArgs("IssuesSize"));
                }
            }
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }

        private void register_Click(object sender, RoutedEventArgs e)
        {
            Register register = new Register();
            register.Show();
        }

        private void username_LostFocus(object sender, RoutedEventArgs e)
        {
            username.Text = " " + username.Text;
            username.Text = username.Text.Trim();
        }

        private void password_LostFocus(object sender, RoutedEventArgs e)
        {
            password.Password = " " + password.Password;
            password.Password = password.Password.Trim();
        }

        private void ChildWindow_Loaded(object sender, RoutedEventArgs e)
        {
            domain = DomainModel.getInstance();
            domain.service = new SampleRIAServiceMock();
            username.Focus(); 
        }

    }

    public class LoginForm
    {
        public String _username;
        public String _password;

        public String Username
        {
            get { return _username; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Lietotājvārds nav ievadīts");
                }
            }
        }
        public String Password
        {
            get { return _password; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet paroli");
                }
            }
        }

    }
}

