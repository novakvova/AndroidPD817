using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Nursery.Application.Account;
using Nursery.Application.Account.Registration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Nursery.WebApi.Controllers
{
    //[Route("api/[controller]")]
    //[Produces("application/json")]
    [AllowAnonymous]
    public class AccountController : BaseController
    {
        //[HttpPost]
        //[Route("Login")]
        //public async Task<IActionResult> Login([FromBody]LoginDTO model)
        //{
        //    return Ok(new {
        //        token = "asdflkiwejwefowfjwof2349824lsdfjlsdfj"
        //    });
        //}
        [HttpPost("registration")]
        public async Task<ActionResult<UserViewModel>> RegistrationAsync(RegistrationCommand command)
        {
            return await Mediator.Send(command);
        }
    }
}
