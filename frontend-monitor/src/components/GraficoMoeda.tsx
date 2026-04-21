import { MoedaHistorico } from "../types/Moeda";
import { CartesianGrid, Line, LineChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts';

interface GrafMoedasProps{
    dados: MoedaHistorico[]
}

export function GrafMoedas({dados}: GrafMoedasProps){
     return (
        <div className="w-full h-[400px] mt-5 bg-slate-800/50 p-6 rounded 3xl border border-slate-900">
      <ResponsiveContainer width="100%" height= "100%" className="min-w-[500px]">
      <LineChart responsive data={dados}>
      <CartesianGrid stroke="#334155" strokeDasharray="3 3" />
      <XAxis dataKey="data" stroke="#94a3b8" tick={{fontSize:12}} />
      <YAxis width="auto" stroke="#94a3b8" domain={['auto', 'auto']} />
      <Tooltip contentStyle={{ backgroundColor: '#1e293b', border: 'none', borderRadius: '8px' }}/>
      <Line
        type="monotone"
        dataKey="valor"
        stroke="#60a5fa"
        strokeWidth={5}
        dot={{
          r: 4,
          fill: "#60a5fa",
        }}
        activeDot={{
          r: 8
        }}
      />
      
    </LineChart>
        </ResponsiveContainer>
    </div>
  );
}