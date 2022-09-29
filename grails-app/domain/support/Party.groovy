package support

class Party {

    private String name
    Map initialInfo = [organizerName: 'Bob']

    static belongsTo = [information: Information]

    String getName() {
        if (information == null) {
            this.name
        } else if (information.isNew()) {
            this.initialInfo?.organizerName
        } else {
            this.name
        }
    }

    static constraints = {
        information nullable: true
        name nullable: false,
                blank: false,
                maxSize: 40,
                validator: { String val, Party obj ->
                    if (val || obj.getName()) {
                        true
                    } else {
                        ['required']
                    }
                }
    }
}
