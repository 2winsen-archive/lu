package lv.lu.cds.model
{
	import lv.lu.cds.buisnessobjects.Issue;
	import lv.lu.cds.buisnessobjects.User;
	import lv.lu.cds.buisnessobjects.constants.IssueConstants;
	import lv.lu.cds.model.service.ISampleRIAService;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class DomainModel
	{
		private static var instance : DomainModel;
		
		public function DomainModel( clazz : Class )
		{
			if ( clazz != SingletonLock	) 
			{
				throw new Error("this is singleton class use getInstance instead!");
			}
		}
		
		public static function getInstance() : DomainModel 
		{
			if ( instance == null ) 
			{
				instance = new DomainModel( SingletonLock );
			}
			return instance;
		}
		
		[Bindable]
		private var _service : ISampleRIAService;
		
		public function set service ( service : ISampleRIAService ) : void
		{
			if ( _service == null )
			{
				this._service = service;
			}
		}
		
		[Bindable]
		private var _user : User;
		
		[Bindable]
		public function set user ( user : User ) : void
		{
			if ( _user == null )
			{
				this._user = user;
			}
		}
		
		public function get user () : User
		{
			return this._user;
		}
		
		private var _isLoggedIn : Boolean = false;
		
		[Bindable]		
		public function set isLoggedIn ( isLoggedIn : Boolean ) : void
		{
			if ( _isLoggedIn == false )
			{
				this._isLoggedIn = isLoggedIn;
			}
		}
		
		public function get isLoggedIn () : Boolean
		{
			return this._isLoggedIn;
		}
		
		[Bindable]
		public var issues : ArrayCollection = new ArrayCollection;
		
		[Bindable]
		public var lastId : int = 1;
		
		public function defaultFaultHandler( event : FaultEvent ) : void 
		{
			Alert.show( "Error occured " + event.fault.faultString + "!!", "Service Error" );	
		}
	
		/*
		* Data exchange functions
		*/
		
		public function login( username : String, passwordHash : String, resultHandler : Function ) : void
		{
			this._service.Login( username, passwordHash, resultHandler, defaultFaultHandler );
		}
		
		public function register( user : User, resultHandler : Function ) : void
		{
			this._service.Register( user, resultHandler, defaultFaultHandler );
		}
		
		public function addIssue( issue : Issue ) : void
		{
			this._service.AddIssue( 
				issue, 
				function ( event : ResultEvent ) : void {
					if ( event.result != null )
					{
						issues.addItem( event.result as Issue );
						lastId++;
					}
				},
				defaultFaultHandler 
			);
		}
		
		public function storeTestIsses() : void
		{
			var data : Issue = new Issue();
			data.id = lastId++;
			data.author = "Vitālijs";
			data.created = new Date();
			data.title = "Search not working";
			data.description = "Search is not working properly";
			data.functionality = "Search";
			data.notes = "";
			data.priority = IssueConstants.PRIORITY_LOW;
			data.responder = "Ainārs";
			data.risk = IssueConstants.RISK_LOW;
			data.status = IssueConstants.STATUS_2;
			data.system = "Persy";
			issues.addItem( data );
			
			var data2 : Issue = new Issue();
			data2.id = lastId++;
			data2.author = "Vitālijs";
			data2.created = new Date();
			data2.title = "Profit not working";
			data2.description = "Profit is not working properly";
			data2.functionality = "Profit";
			data2.notes = "";
			data2.priority = IssueConstants.PRIORITY_HIGH;
			data2.responder = "Ainārs";
			data2.risk = IssueConstants.RISK_HIGH;
			data2.status = IssueConstants.STATUS_1;
			data2.system = "Persy Server";
			issues.addItem( data2 );
			
			var data3 : Issue = new Issue();
			data3.id = lastId++;
			data3.author = "Pēteris";
			data3.created = new Date();
			data3.title = "Performance issues";
			data3.description = "Performance issues";
			data3.functionality = "General";
			data3.notes = "";
			data3.priority = IssueConstants.PRIORITY_LOW;
			data3.responder = "Ainārs";
			data3.risk = IssueConstants.RISK_LOW;
			data3.status = IssueConstants.STATUS_4;
			data3.system = "Persy";
			issues.addItem( data3 );
			
			var data4 : Issue = new Issue();
			data4.id = lastId++;
			data4.author = "Vitālijs";
			data4.created = new Date();
			data4.title = "Button color is incorrect";
			data4.description = "Button color should be slate";
			data4.functionality = "General";
			data4.notes = "";
			data4.priority = IssueConstants.PRIORITY_LOW;
			data4.responder = "Ainārs";
			data4.risk = IssueConstants.RISK_LOW;
			data4.status = IssueConstants.STATUS_4;
			data4.system = "Persy";
			issues.addItem( data4 );
		}
	}
}
class SingletonLock
{
}
