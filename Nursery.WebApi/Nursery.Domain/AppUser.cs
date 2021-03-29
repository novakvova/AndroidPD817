using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;

namespace Nursery.Domain
{
    public class AppUser : IdentityUser<long>
    {
        public string DisplayName { get; set; }
        public virtual ICollection<AppUserRole> UserRoles { get; set; }
    }
}
