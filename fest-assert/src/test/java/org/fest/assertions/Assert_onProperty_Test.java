package org.fest.assertions;

public interface Assert_onProperty_Test {

  // test success usage of onProperty

  void should_pass_on_non_primitive_type_property();

  void should_pass_on_enum_type_property();

  void should_pass_on_primitive_type_int_property();

  void should_pass_on_primitive_type_long_property();

  void should_pass_on_primitive_type_short_property();

  void should_pass_on_primitive_type_float_property();

  void should_pass_on_primitive_type_double_property();

  void should_pass_on_primitive_type_boolean_property();

  void should_pass_on_primitive_type_byte_property();

  void should_pass_on_primitive_type_char_property();

  void should_pass_on_non_primitive_type_nested_property();

  void should_pass_on_enum_type_nested_property();

  void should_pass_on_primitive_type_nested_property();

  void should_pass_even_if_actual_contains_null_elements();

  void should_pass_with_null_nested_property_in_actual_elements();

  void should_pass_with_null_property_in_actual_elements();

  void should_pass_if_actual_is_empty();

  // test erroneous usage of onProperty

  void should_fail_on_non_primitive_type_property();

  void should_fail_on_non_primitive_type_nested_property();

  void should_fail_on_enum_type_property();

  void should_fail_on_enum_type_nested_property();

  void should_fail_on_primitive_type_property();

  void should_fail_on_primitive_type_nested_property();

  void should_fail_because_of_unknown_property();

  void should_fail_if_actual_is_null();
}