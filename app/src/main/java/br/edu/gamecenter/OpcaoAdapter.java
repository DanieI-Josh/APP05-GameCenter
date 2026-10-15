package br.edu.gamecenter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.edu.gamecenter.databinding.ItemOpcaoBinding;
public final class OpcaoAdapter extends RecyclerView.Adapter<OpcaoAdapter.OpcaoViewHolder>  {
    public interface AoPressionarOpcao  {
        void remover(Opcao opcao);
    }
    private final List<Opcao> opcoes;
    private final AoPressionarOpcao listener;
    public OpcaoAdapter(List<Opcao> opcoes,AoPressionarOpcao listener) {
        this.opcoes=opcoes;
        this.listener=listener;
    }
    @NonNull @Override public OpcaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        return new OpcaoViewHolder(ItemOpcaoBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }
    @Override public void onBindViewHolder(@NonNull OpcaoViewHolder holder,int position) {
        holder.bind(opcoes.get(position));
    }
    @Override public int getItemCount() {
        return opcoes.size();
    }
    public final class OpcaoViewHolder extends RecyclerView.ViewHolder  {
        private final ItemOpcaoBinding binding;
        OpcaoViewHolder(ItemOpcaoBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        void bind(Opcao opcao) {
            binding.textValor.setText(opcao.getValor());
            binding.textCategoria.setText(opcao.getCategoria());
            binding.getRoot().setContentDescription(binding.getRoot().getContext().getString(R.string.item_formato,opcao.getValor(),opcao.getCategoria()));
            binding.getRoot().setOnLongClickListener(view-> {
                if(getBindingAdapterPosition()!=RecyclerView.NO_POSITION)listener.remover(opcao);return true;
            }
            );
        }
    }
}
