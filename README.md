# call-forward
Forward specific calls to specified extention based on Caller ID.

The only change you have to make in your Asterisk VoIP Server is to add the line below in your desired plcae in DialPlan:
exten => _X.,n,AGI(agi:async)

