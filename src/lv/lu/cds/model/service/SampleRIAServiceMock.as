package lv.lu.cds.model.service
{
	import lv.lu.cds.buisnessobjects.Issue;
	import lv.lu.cds.buisnessobjects.User;
	import lv.lu.cds.model.DomainModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;

	public class SampleRIAServiceMock implements ISampleRIAService
	{
		private var users : ArrayCollection = new ArrayCollection;
		
		public function Login( username : String, passwordHash : String, resultHandler : Function = null, faultHandler : Function = null ) : void
		{
			for each ( var user : User in users )
			{
				if ( user.username == username && user.passwordHash == passwordHash )
				{
					resultHandler.call( null, new ResultEvent( "Test", false, true, user ) );
				}
			}
		}
		
		public function Register( user : User, resultHandler : Function = null, faultHandler : Function = null ) : void
		{
			users.addItem( user );
			resultHandler.call( null, new ResultEvent( "Test", false, true, "Test" ) );
		}
		
		public function AddIssue( issue : Issue, resultHandler : Function = null, faultHandler : Function = null ) : void
		{
			resultHandler.call( null, new ResultEvent( "Test", false, true, issue ) );
		}
	}
}
