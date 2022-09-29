package support

class Information {

    static hasMany = [parties: Party]

    boolean isNew() {
        true
    }

}
