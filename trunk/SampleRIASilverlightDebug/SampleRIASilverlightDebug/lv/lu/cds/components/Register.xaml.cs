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
using SampleRIASilverlightDebug.lv.lu.cds.utils;
using System.Security.Cryptography;
using System.Text;


namespace SampleRIASilverlightDebug.lv.lu.cds.components
{
    public partial class Register : ChildWindow
    {
        private RegisterForm form;

        public Register()
        {
            InitializeComponent();
            form = new RegisterForm();
            LayoutRoot.DataContext = form;
        }

        private void OKButton_Click(object sender, RoutedEventArgs e)
        {
            if (ValidationUtils.notBlank(name.Text) && 
                ValidationUtils.notBlank(surname.Text) &&
                ValidationUtils.notBlank(username.Text) &&
                ValidationUtils.notBlank(password.Password) &&
                ValidationUtils.notBlank(password2.Password) &&
                String.Equals(password.Password, password2.Password)
                )
            {
                User user = new User();
				user.name = name.Text;
				user.surname = surname.Text;
				user.username = username.Text;

                SHA256 sha = new SHA256Managed();
                Byte[] inputBytes = Encoding.UTF8.GetBytes(password.Password);
                Byte[] hashedBytes = sha.ComputeHash(inputBytes);
                user.passwordHash = BitConverter.ToString(hashedBytes);			
	
				DomainModel.getInstance().register( user );
                this.DialogResult = true;
            }
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }

        private void name_LostFocus(object sender, RoutedEventArgs e)
        {
            name.Text = " " + name.Text;
            name.Text = name.Text.Trim();
        }

        private void surname_LostFocus(object sender, RoutedEventArgs e)
        {
            surname.Text = " " + surname.Text;
            surname.Text = surname.Text.Trim();
        }

        private void username_LostFocus(object sender, RoutedEventArgs e)
        {
            username.Text = " " + username.Text;
            username.Text = username.Text.Trim();
        }

        private void password_LostFocus_1(object sender, RoutedEventArgs e)
        {
            password.Password = " " + password.Password;
            password.Password = password.Password.Trim();
        }

        private void password2_LostFocus_1(object sender, RoutedEventArgs e)
        {
            password2.Password = " " + password2.Password;
            password2.Password = password2.Password.Trim();
        }
    }

    public class RegisterForm
    {
        public String _name;
        public String _surname;
        public String _username;
        public String _password;
        public String _password2;

        public String Name
        {
            get { return _name; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet vārdu");
                }
            }
        }
        public String Surname
        {
            get { return _surname; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet uzvārdu");
                }
            }
        }
        public String Username
        {
            get { return _username; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet lietotājvārdu");
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
        public String Password2
        {
            get { return _password2; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet paroli otrreiz");
                }
            }
        }

    }

}

