﻿using FluentValidation;
using Nursery.Application.Validators;
using System;
using System.Collections.Generic;
using System.Text;

namespace Nursery.Application.Account.Registration
{
	public class RegistrationValidation : AbstractValidator<RegistrationCommand>
	{
		public RegistrationValidation()
		{
			RuleFor(x => x.DisplayName).NotEmpty();
			RuleFor(x => x.Email).NotEmpty().EmailAddress();
			RuleFor(x => x.Password).NotEmpty().Password();
		}
	}
}
