package lv.lu.cds.model.service
{
	import lv.lu.cds.buisnessobjects.Issue;
	import lv.lu.cds.buisnessobjects.User;

	public interface ISampleRIAService
	{
		function Login( username : String, passwordHash : String, resultHandler : Function = null, faultHandler : Function = null ) : void;
		function Register( user : User, resultHandler : Function = null, faultHandler : Function = null ) : void;
		function AddIssue( issue : Issue, resultHandler : Function = null, faultHandler : Function = null ) : void;
	}
}