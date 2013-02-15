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
using SampleRIASilverlightDebug.lv.lu.cds.utils;
using SampleRIASilverlightDebug.lv.lu.cds.model;
using SampleRIASilverlightDebug.lv.lu.cds.businessobjects;

namespace SampleRIASilverlightDebug.lv.lu.cds.components
{
    public partial class NewIssue : ChildWindow
    {
        private NewIssueForm form;
        private DomainModel domain = DomainModel.getInstance();
        public bool viewOnly = false;

        public NewIssue()
        {
            InitializeComponent();
            form = new NewIssueForm();
            LayoutRoot.DataContext = form;
            author.DataContext = domain;
            id.DataContext = domain;
        }

        private void create_Click(object sender, RoutedEventArgs e)
        {
            createAndSave_Click( null, null );
            if (ValidationUtils.notBlank(title.Text) &&
                ValidationUtils.notBlank(details.Text) &&
                ValidationUtils.notBlank(system.Text) &&
                ValidationUtils.notBlank(functionality.Text) &&
                ValidationUtils.notBlank(owner.Text)
                )
            {
                this.DialogResult = true;
            }
        }

        private void cancel_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }

        private void createAndSave_Click(object sender, RoutedEventArgs e)
        {
            if (ValidationUtils.notBlank(title.Text) &&
                ValidationUtils.notBlank(details.Text) &&
                ValidationUtils.notBlank(system.Text) &&
                ValidationUtils.notBlank(functionality.Text) &&
                ValidationUtils.notBlank(owner.Text)
                )
            {
                Issue issue = new Issue();
                issue.id = Int32.Parse(id.Text);
                issue.author = author.Text;
                issue.status = status.Text;
                issue.created = date.SelectedDate;
                issue.title = title.Text;
                issue.description = details.Text;
                issue.functionality = functionality.Text;
                issue.notes = notes.Text;
                issue.priority = priority.SelectedItem as String;
                issue.responder = owner.Text;
                issue.risk = risk.SelectedItem as String;
                issue.system = system.Text;
                DomainModel.getInstance().addIssue(issue);
            }
        }

        public void populateData(Issue issue)
        {
            if ( viewOnly && issue != null )
            {
                goToEntryMode(false);
                id.Text = issue.id.ToString();
                author.Text = issue.author;
                status.Text = issue.status;
                date.SelectedDate = issue.created;
                title.Text = issue.title;
                details.Text = issue.description;
                functionality.Text = issue.functionality;
                notes.Text = issue.notes;
                priority.SelectedItem = issue.priority;
                owner.Text = issue.responder;
                risk.SelectedItem = issue.risk;
                system.Text = issue.system;
                system.IsEnabled = !viewOnly;
            }
        }

        public void goToEntryMode(bool entry)
        {
            id.IsEnabled = entry;
            author.IsEnabled = entry;
            status.IsEnabled = entry;
            date.IsEnabled = entry;
            title.IsEnabled = entry;
            details.IsEnabled = entry;
            functionality.IsEnabled = entry;
            notes.IsEnabled = entry;
            priority.IsEnabled = entry;
            owner.IsEnabled = entry;
            risk.IsEnabled = entry;
            system.IsEnabled = entry;

            create.Visibility = (entry) ? System.Windows.Visibility.Visible : System.Windows.Visibility.Collapsed;
            createAndSave.Visibility = (entry) ? System.Windows.Visibility.Visible : System.Windows.Visibility.Collapsed;
        }

        private void title_LostFocus(object sender, RoutedEventArgs e)
        {
            title.Text = " " + title.Text;
            title.Text = title.Text.Trim();
        }

        private void details_LostFocus(object sender, RoutedEventArgs e)
        {
            details.Text = " " + details.Text;
            details.Text = details.Text.Trim();
        }

        private void functionality_LostFocus(object sender, RoutedEventArgs e)
        {
            functionality.Text = " " + functionality.Text;
            functionality.Text = functionality.Text.Trim();
        }

        private void system_LostFocus(object sender, RoutedEventArgs e)
        {
            system.Text = " " + system.Text;
            system.Text = system.Text.Trim();
        }

        private void owner_LostFocus(object sender, RoutedEventArgs e)
        {
            owner.Text = " " + owner.Text;
            owner.Text = owner.Text.Trim();
        }
    }
    public class NewIssueForm
    {
        public DateTime _Date;
        public DateTime Date
        {
            get { return new DateTime(); }
            set
            {
                if (value == null)
                {
                    throw new Exception("Please enter date");
                }
            }
        }
        public String _Title;
        public String Title
        {
            get { return _Title; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet nosaukumu");
                }
            }
        }
        public String _Details;
        public String Details
        {
            get { return _Details; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet detalizētu aprakstu");
                }
            }
        }
        public String _System;
        public String System
        {
            get { return _System; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet sistēmu");
                }
            }
        }
        public String _Functionality;
        public String Functionality
        {
            get { return _Functionality; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet funkcionalitāti");
                }
            }
        }
        public String _Owner;
        public String Owner
        {
            get { return _Owner; }
            set
            {
                if (!ValidationUtils.notBlank(value))
                {
                    throw new Exception("Ievadiet atbildīgo personu");
                }
            }
        }
    }
}

