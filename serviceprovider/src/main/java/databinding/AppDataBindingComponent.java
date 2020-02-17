package databinding;

public class AppDataBindingComponent implements androidx.databinding.DataBindingComponent {
    public RecyclerViewDataBinding getRecyclerViewDataBinding() {
        return new RecyclerViewDataBinding();
    }
}
