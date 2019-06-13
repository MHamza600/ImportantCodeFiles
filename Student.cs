#region Copyright Syncfusion Inc. 2001-2017.
// Copyright Syncfusion Inc. 2001-2017. All rights reserved.
// Use of this code is subject to the terms of our license.
// A copy of the current license can be obtained at any time by e-mailing
// licensing@syncfusion.com. Any infringement will be prosecuted under
// applicable laws. 
#endregion
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Input;
using System.ComponentModel;
using System.Windows;
using System.Collections.ObjectModel;
using System.Text.RegularExpressions;
using Syncfusion.Windows.Shared;
using Syncfusion.XlsIO;

namespace DataBindingDemo
{
    public partial class Student : NotificationObject, IDataErrorInfo
    {

        #region RegisterCommandValidation
        public bool ValidateParentID()
        {
            if (string.IsNullOrEmpty(parentID.ToString()) || Convert.ToInt32(parentID.ToString()) <= 0)
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else
            {
                return true;
            }
        }
        public bool Validate()
        {
            if (string.IsNullOrEmpty(fName.ToString()))
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(lName.ToString()))
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(dob.ToString()) || (DateTime.Parse(dob.ToString()) <= DateTime.Parse("1/1/0001") || DateTime.Parse(dob.ToString()) >= DateTime.Today))
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(admDate.ToString()) || (DateTime.Parse(admDate.ToString()) <= DateTime.Parse("1/1/0001") || DateTime.Parse(admDate.ToString()) >= DateTime.Today))
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(gender.ToString()))
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(admFee.ToString()) || Convert.ToInt32(admFee.ToString()) < 0)
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(monthlyFee.ToString()) || Convert.ToInt32(monthlyFee.ToString()) < 0)
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (!string.IsNullOrEmpty(otherCharges.ToString()) && Convert.ToInt32(otherCharges.ToString()) < 0)
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(disPercent.ToString()) || Convert.ToInt32(disPercent.ToString()) < 0 || Convert.ToInt32(admFee.ToString()) > 0)
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else if (string.IsNullOrEmpty(classID.ToString()) || Convert.ToInt32(classID.ToString()) <= 0)
            {
                MessageBox.Show("Form has invalid data", "Warning", MessageBoxButton.OK, MessageBoxImage.Warning, MessageBoxResult.OK, MessageBoxOptions.DefaultDesktopOnly);
                return false;
            }
            else
            {
                return true;
            }
        }
        #endregion

        #region properties

        private long stdID = 12345;
        public long StudentID
        {
            get
            {
                return stdID;
            }
            set
            {
                stdID = value;
                this.RaisePropertyChanged(() => this.StudentID);
            }
        }

        private string fName = "Carl";
        public string FirstName
        {
            get
            {
                return fName;
            }
            set
            {
                fName = value;
                this.RaisePropertyChanged(() => this.FirstName);
            }
        }

        private string lName = "Johnson";
        public string LastName
        {
            get
            {
                return lName;
            }
            set
            {
                lName = value;
                this.RaisePropertyChanged(() => this.LastName);
            }
        }

        private string dob = DateTime.Parse("10/27/2002", new System.Globalization.CultureInfo("en-US", true)).ToString();
        public string DateOfBirth
        {
            get
            {
                return dob;
            }
            set
            {
                dob = value;
                this.RaisePropertyChanged(() => this.DateOfBirth);
            }
        }

        private string admDate = DateTime.Today.ToString();
        public string AdmissionDate
        {
            get
            {
                return admDate;
            }
            set
            {
                admDate = value;
                this.RaisePropertyChanged(() => this.AdmissionDate);
            }
        }

        private string gender = "Male";
        public string Gender
        {
            get
            {
                return gender;
            }
            set
            {
                gender = value;
                this.RaisePropertyChanged(() => this.Gender);
            }
        }

        private long admFee = 100;
        public long AdmissionFee
        {
            get
            {
                return admFee;
            }
            set
            {
                admFee = value;
                this.RaisePropertyChanged(() => this.AdmissionFee);
            }
        }

        private long monthlyFee = 2000;
        public long MonthlyFee
        {
            get
            {
                return monthlyFee;
            }
            set
            {
                monthlyFee = value;
                this.RaisePropertyChanged(() => this.MonthlyFee);
            }
        }
        private long totalFee = 2000;
        public long TotalFee
        {
            get
            {
                return totalFee;
            }
            set
            {
                totalFee = value;
                this.RaisePropertyChanged(() => this.TotalFee);
            }
        }
        private long afterdiscountedFee = 0;
        public long  AfterDiscountedFee
        {
            get
            {
                return afterdiscountedFee;
            }
            set
            {
                afterdiscountedFee = value;
                this.RaisePropertyChanged(() => this.AfterDiscountedFee);
            }
        }

        private long sibblings = 0;
        public long Sibblings
        {
            get
            {
                return sibblings;
            }
            set
            {
                sibblings = value;
                this.RaisePropertyChanged(() => this.Sibblings);
            }
        }

        private string time = "";
        public string Time
        {
            get
            {
                return time;
            }
            set
            {
                time = value;
                this.RaisePropertyChanged(() => this.Time);
            }
        }

        private string paidtime = "";
        public string PaidTime
        {
            get
            {
                return paidtime;
            }
            set
            {
                paidtime = value;
                this.RaisePropertyChanged(() => this.PaidTime);
            }
        }
        private long otherCharges = 0;
        public long OtherCharges
        {
            get
            {
                return otherCharges;
            }
            set
            {
                otherCharges = value;
                this.RaisePropertyChanged(() => this.OtherCharges);
            }
        }

        private long disPercent = 5;
        public long DiscountPercentage
        {
            get
            {
                return disPercent;
            }
            set
            {
                disPercent = value;
                this.RaisePropertyChanged(() => this.DiscountPercentage);
            }
        }

        private long parentID = 56789;
        public long ParentID
        {
            get
            {
                return parentID;
            }
            set
            {
                parentID = value;
                this.RaisePropertyChanged(() => this.ParentID);
            }
        }

        private long classID = 111;
        public long ClassID
        {
            get
            {
                return classID;
            }
            set
            {
                classID = value;
                this.RaisePropertyChanged(() => this.ClassID);
            }
        }

        private Nullable<bool> active;
        public Nullable<bool> isActive
        {
            get
            {
                return active;
            }
            set
            {
                active = value;
                this.RaisePropertyChanged(() => this.isActive);
            }
        }

        #endregion


        #region error
        public string Error
        {
            get { return null; }
        }

        public string this[string columnName]
        {

            get
            {
                string error = null;
                switch (columnName)
                {
                    case "StudentID":
                        if (stdID <= 0 || stdID > 10000000)
                        {
                            error = "Student ID must be in range";
                        }
                        break;
                    case "FirstName":
                        if (string.IsNullOrEmpty(fName))
                        {
                            error = "First Name is required.";
                        }
                        break;
                    case "LastName":
                        if (string.IsNullOrEmpty(lName))
                        {
                            error = "Last Name is required.";
                        }
                        break;
                    case "DateOfBirth":
                        if (DateTime.Parse(dob) <= DateTime.Parse("1/1/0001") || DateTime.Parse(dob) >= DateTime.Today)
                        {
                            error = "Valid Date of Birth required.";
                        }
                        break;
                    case "AdmissionDate":
                        if (DateTime.Parse(admDate) <= DateTime.Parse("1/1/0001") || DateTime.Parse(admDate) >= DateTime.Today)
                        {
                            error = "Valid Admission Date required.";
                        }
                        break;
                    case "Gender":
                        if (string.IsNullOrEmpty(gender))
                        {
                            error = "Gender is required.";
                        }
                        break;
                    case "AdmissionFee":
                        if (admFee <= 0 || admFee > 10000000)
                        {
                            error = "Admission fee must be in range";
                        }
                        break;
                    case "MonthlyFee":
                        if (monthlyFee <= 0 || monthlyFee > 10000000)
                        {
                            error = "Monthly fee must be in range";
                        }
                        break;
                    case "OtherCharges":
                        if (otherCharges < 0 || otherCharges > 10000000)
                        {
                            error = "Other charges must be in range";
                        }
                        break;
                    case "DiscountPercentage":
                        if (disPercent < 0 || disPercent > 100)
                        {
                            error = "Discount percentage must be between 0% and 100%";
                        }
                        break;
                    case "ParentID":
                        if (parentID <= 0 || parentID > 10000000)
                        {
                            error = "Parent ID must be in range";
                        }
                        break;
                    case "ClassID":
                        if (classID <= 0 || classID > 10000000)
                        {
                            error = "Class ID must be in range";
                        }
                        break;
                }
                return error;
            }
        }
        #endregion

    }



}

