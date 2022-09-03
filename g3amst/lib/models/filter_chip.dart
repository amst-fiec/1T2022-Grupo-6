class FilterChipData {
  String label;
  bool isSelected;
  FilterChipData(this.label, this.isSelected);
}
List<FilterChipData> categories = [
  FilterChipData("Shirts", false),
  FilterChipData("Pants", false),
  FilterChipData("Shoes", false),
  FilterChipData("Watchs", false)
];