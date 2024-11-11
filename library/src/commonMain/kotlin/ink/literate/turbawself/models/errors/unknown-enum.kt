package ink.literate.turbawself.models.errors

class UnknownEnumValue (label: String, value: Any): Exception("Expected a value from the enum $label, but got '$value'")